package com.umc.save.Record.Auth.Video

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Video(
    @SerializedName(value = "video") var video: ArrayList<MultipartBody.Part>,
    @SerializedName(value = "vidAbuseIdx") var vidAbuseIdx: Int,
    @SerializedName(value = "vidChildIdx") var vidChildIdx: Int
)