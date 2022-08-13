package com.umc.save.Record.Auth.Recording

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Recording(
    @SerializedName(value = "picture") var picture: ArrayList<MultipartBody.Part>,
    @SerializedName(value = "picAbuseIdx") var picAbuseIdx: Int,
    @SerializedName(value = "picChildIdx") var picChildIdx: Int
)
