package com.umc.save.Sign.Auth

import com.google.gson.annotations.SerializedName

//로그인 response params
data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result
)
data class Result(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)
