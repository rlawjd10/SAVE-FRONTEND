package com.umc.save.Sign.Auth

import com.google.gson.annotations.SerializedName

//로그인 response params
//OUTPUT 서버에서 클라이언트에게 내려주는 응답 값
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
