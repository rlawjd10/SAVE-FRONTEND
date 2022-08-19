package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName

data class EditUserResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : mResult
    )
data class mResult (
    @SerializedName(value = "completeMessage") val completeMessage : String
)