package com.umc.save.Locker.data

import com.google.gson.annotations.SerializedName

data class Video (
    @SerializedName(value = "videoPath") var location : String,
    @SerializedName(value = "thumbnailPath") var thumb : String
)