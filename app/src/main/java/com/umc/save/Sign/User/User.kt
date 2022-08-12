package com.umc.save.Sign.User

import com.google.gson.annotations.SerializedName

//회원가입 Body
data class User(
    @SerializedName(value = "isSnsAuth") val int: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "phone") val phone: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
)
