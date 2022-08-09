package com.umc.save.Record.Auth.ChildRecord

import com.google.gson.annotations.SerializedName

data class ChildRecordResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "childIdx") val childIdx : Int
)
