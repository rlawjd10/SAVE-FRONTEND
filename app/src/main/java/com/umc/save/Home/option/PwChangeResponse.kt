package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName

data class PwChangeResponse(
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : onpwChange
)
data class onpwChange(
    @SerializedName(value = "userIdx") val userIdx : Int,
    @SerializedName(value = "newPassword") val newPassword : String,
    @SerializedName(value = "successMessage") val successMessage : String
)
