package com.umc.save.Home

import androidx.annotation.DrawableRes

data class NewsData(
    @DrawableRes
    val image: Int,
    val date: String,
    val text: String,
    val url: String
)
