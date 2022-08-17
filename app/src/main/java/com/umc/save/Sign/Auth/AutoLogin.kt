package com.umc.save.Sign.Auth

import com.google.gson.annotations.SerializedName

data class AutoLogin(
    @SerializedName(value = "userIdx") val useridxVar: userIdx_var.UserIdx,
    @SerializedName(value = "message") val message: String
)
