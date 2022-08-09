package com.umc.save.Record.Auth.SuspectRecord

import com.google.gson.annotations.SerializedName

data class SuspectRecordResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "suspectIdx") val suspectIdx : Int
)