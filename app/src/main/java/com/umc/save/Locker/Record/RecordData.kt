package com.umc.save.Locker.Record

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

data class RecordData (
    @SerializedName(value = "abuseIdx") var abuseIdx : Int,
    @SerializedName(value = "childIdx") var childIdx : Int,
    @SerializedName(value = "date") var abuseDate : String,
    @SerializedName(value = "time") var abuseTime : String,
    @SerializedName(value = "place") var abusePlace : String,
    @SerializedName(value = "create_date") var createAt : Date
    )
