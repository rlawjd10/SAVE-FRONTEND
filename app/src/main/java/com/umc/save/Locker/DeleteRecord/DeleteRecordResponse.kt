package com.umc.save.Locker.DeleteRecord

import com.google.gson.annotations.SerializedName

data class DeleteRecordResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : DeleteRecord
)
