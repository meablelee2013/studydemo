package com.ruma.moshidemo.http

import com.ruma.moshidemo.http.base.ResponseResult
import com.ruma.moshidemo.model.DiscoverTemplateModel
import com.ruma.moshidemo.model.DiscoverThemeModel

data class UserApiResponse(
    var discoverTemplateResult: ResponseResult<DiscoverTemplateModel>,
    var allThemeResult: ResponseResult<List<DiscoverThemeModel>>
)
