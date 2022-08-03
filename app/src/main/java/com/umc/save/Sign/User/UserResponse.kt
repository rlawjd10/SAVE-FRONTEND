package com.umc.save.Sign.User

import com.google.gson.annotations.SerializedName

// 회원가입 Response params
data class UserResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result?
)
data class Result(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)
