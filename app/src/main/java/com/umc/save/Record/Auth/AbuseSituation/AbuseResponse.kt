package com.umc.save.Record.Auth.AbuseSituation

import com.google.gson.annotations.SerializedName

data class AbuseResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "abuseIdx") var abuseIdx : Int
)