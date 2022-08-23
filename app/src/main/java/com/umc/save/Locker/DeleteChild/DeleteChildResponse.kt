package com.umc.save.Locker.DeleteChild

import com.google.gson.annotations.SerializedName

data class DeleteChildResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : DeleteChild?
)
