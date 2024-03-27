package com.ruma.moshidemo.http.base

import com.ruma.moshidemo.http.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * val apiService = NetworkManager.createApiService<ApiService>()
 * val call = apiService.queryData()
 * val response = NetworkManager.executeApiCall(call)
 * val httpCode = response.code() // 获取 HTTP 状态码
 *
 */
private const val BASE_URL = "https://svc-staging.dev-hyperstudio.com"

val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkManager.createApiService()
}

object NetworkManager {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 401) {
            // 处理401未授权错误，比如重新登录或者跳转到登录页面
            // 这里只是示例，你可以根据你的应用需求进行相应的处理
            // 如果需要重新登录，可以清除用户的登录状态，并导航到登录页面
            // 如果需要刷新令牌，可以发起刷新令牌的请求
            // 如果需要跳转到登录页面，可以启动一个新的Activity并显示登录界面
        }

        response // 继续传递原始响应
    }.build()

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    inline fun <reified T> createApiService(): T {
        return retrofit.create(T::class.java)
    }

    fun <T> executeApiWithHttpCode(call: Call<ResponseResult<T>>): ResponseResult<T> {
        val response = call.execute()
        val httpResponse = response.raw()
        val httpCode = httpResponse.code
        val responseBody = response.body()?.apply {
            // 设置 HTTP 状态码
            this.httpCode = httpCode
        }
        return responseBody ?: throw IOException("Response body is null")
    }
}