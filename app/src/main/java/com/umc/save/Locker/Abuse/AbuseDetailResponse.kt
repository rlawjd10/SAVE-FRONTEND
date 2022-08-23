package com.umc.save.Locker.Abuse

import com.google.gson.annotations.SerializedName
import com.umc.save.Locker.Record.RecordDetailData

data class AbuseDetailResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : RecordDetailData
)

