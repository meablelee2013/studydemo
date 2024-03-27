package com.ruma.moshidemo.http

import com.ruma.moshidemo.http.base.ResponseResult
import com.ruma.moshidemo.model.DiscoverTemplateModel
import com.ruma.moshidemo.model.DiscoverThemeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/hyper-booth/templates")
    suspend fun queryDiscoverTemplates(): ResponseResult<DiscoverTemplateModel>

    @GET("/hyper-booth/query/all/themes")
    suspend fun queryAllThemes(): ResponseResult<List<DiscoverThemeModel>>

    @GET("/hyper-booth/query/all/themes")
    suspend fun queryAllThemesByHttpCode(): Call<ResponseResult<List<DiscoverThemeModel>>>
}