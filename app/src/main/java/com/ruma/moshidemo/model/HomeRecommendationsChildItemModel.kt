package com.ruma.moshidemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeRecommendationsItemModel(
    var type: String = "",//trending,new,seasonal,for-you
    var templates: MutableList<HomeRecommendationsChildItemModel> = mutableListOf()
) : Parcelable

@Parcelize
data class HomeRecommendationsChildItemModel(
    val id: Int = 0,
    val name: String = "",
    val genders: List<String> = listOf(),
    val themeId: Int = 0,
    val coverImages: MutableList<String> = mutableListOf(),
    val themeName: String = ""
) : Parcelable

@Parcelize
data class DetailTemplateModel(
    val id: Int = 0,
    val name: String = "",
    val genders: List<String> = listOf(),
    val themeId: Int = 0,
    val aspectRatioId: Int = 0,
    val coverImages: MutableList<String> = mutableListOf(),
    val showcaseImages: MutableList<ShowCaseImageModel> = mutableListOf(),
    val credits: Int = 0
) : Parcelable

@Parcelize
data class ShowCaseImageModel(
    val imageUrl: String = "",
    val aspectRatio: String = "",

) : Parcelable

