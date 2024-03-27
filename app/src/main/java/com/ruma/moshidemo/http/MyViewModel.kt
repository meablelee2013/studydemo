package com.ruma.moshidemo.http

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruma.moshidemo.http.base.ResponseResult
import com.ruma.moshidemo.http.base.apiService
import com.ruma.moshidemo.model.DiscoverTemplateModel
import com.ruma.moshidemo.model.DiscoverThemeModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException

class MyViewModel : ViewModel() {


    var combineResponse: MutableLiveData<UserApiResponse> = MutableLiveData()
    var discoverThemeModel: MutableLiveData<ResponseResult<List<DiscoverThemeModel>>> = MutableLiveData()


    suspend fun refreshData() = coroutineScope {
        val discoverTemplate = async { apiService.queryDiscoverTemplates() }
        val allTheme = async { apiService.queryAllThemes() }

        // 等待两个请求全部完成
        val (discoverTemplateResult, allThemeResult) = awaitAll(discoverTemplate, allTheme)


        // 请求完成后的处理逻辑，比如更新LiveData或者通知调用者
        Log.d("alex", "is mainThread = ${Looper.myLooper() == Looper.getMainLooper()}")
        val newData = UserApiResponse(
            discoverTemplateResult as ResponseResult<DiscoverTemplateModel>, allThemeResult as ResponseResult<List<DiscoverThemeModel>>
        )
        // 使用 postValue 来更新 LiveData
        combineResponse.postValue(newData)
    }


    suspend fun <T> request(
        apiCall: suspend () -> ResponseResult<T>,
        onSuccess: (ResponseResult<T>) -> Unit,
        onFailure: (String) -> Unit
    ) = coroutineScope {
        runCatching {
            val apiCallResponse = async { apiCall() }
            apiCallResponse.await()
        }.onSuccess {
            onSuccess(it)
        }.onFailure {
            if (it != null && it is HttpException) {
                val response = it.response()
            }
        }
    }

}