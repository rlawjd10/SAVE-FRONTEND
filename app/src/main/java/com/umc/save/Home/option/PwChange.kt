package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName

//body
data class PwChange(
    @SerializedName(value = "originPassword") val originPassword: String,
    @SerializedName(value = "newPassword") val newPassword: String
)
