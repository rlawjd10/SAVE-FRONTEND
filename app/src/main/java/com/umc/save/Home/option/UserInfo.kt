package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "phone") val phone: String,
    @SerializedName(value = "email") val email: String,
)