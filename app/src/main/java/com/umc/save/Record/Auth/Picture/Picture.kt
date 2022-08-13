package com.umc.save.Record.Auth.Picture

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart

data class Picture(
//    @SerializedName(value = "picture") var picture: ArrayList<MultipartBody.Part>,
    @SerializedName(value = "picAbuseIdx") var picAbuseIdx: Int,
    @SerializedName(value = "picChildIdx") var picChildIdx: Int

    )
