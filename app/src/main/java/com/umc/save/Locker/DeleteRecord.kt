package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*

data class DeleteRecord(
    @SerializedName(value = "date") var abuseDate : String,
    @SerializedName(value = "time") var abuseTime : String,
    @SerializedName(value = "place") var abusePlace : String,
    @SerializedName(value = "type") var abuseType : String,
    @SerializedName(value = "detail") var detailDescription : String? = "",
    @SerializedName(value = "etc") var detailEtcDescription : String? = "",
    @SerializedName(value = "deleteAbuse") var deleteAbuseMessage: String
)