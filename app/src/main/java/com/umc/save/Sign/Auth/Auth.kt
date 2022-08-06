package com.umc.save.Sign.Auth

import com.google.gson.annotations.SerializedName

//AUTH
//로그인 Body
data class Auth(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String
)
