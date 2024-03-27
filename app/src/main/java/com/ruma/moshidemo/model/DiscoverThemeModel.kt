package com.ruma.moshidemo.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class DiscoverThemeModel(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val credits: Int = 0
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class DiscoverTemplateModel(
    var rows: MutableList<HomeRecommendationsChildItemModel> = mutableListOf(),
    val totalNum: Int = 0
) : Parcelable

