package com.umc.save.Sign.Auth

import com.google.gson.annotations.SerializedName

data class AutoLoginResponse (
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code: Int,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "result") val result: aResult
    )

data class aResult(
    @SerializedName(value = "userIdx") val userIdx: Int,
    @SerializedName(value = "message") val message: String
)