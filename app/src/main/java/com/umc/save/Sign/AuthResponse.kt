package com.umc.save.Sign

import com.google.gson.annotations.SerializedName

//로그인, 회원가입 response params
//OUTPUT 서버에서 클라이언트에게 내려주는 응답 값
//로그인, 회원가입 api가 다른 데이터 시트를 사용하고 둘 다 result값을
//사용하기 때문에 null처리를 해주지 않는다. jwt만 해준다.
data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result
)
data class Result(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String?
)
