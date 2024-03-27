package com.ruma.moshidemo.http.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseResult<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T,
    var httpCode: Int = -1 // 默认值为 -1，表示未设置
)