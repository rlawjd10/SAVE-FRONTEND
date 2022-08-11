package com.umc.save.Record.Auth.AbuseSituation

import com.google.gson.annotations.SerializedName

data class AbuseSituation(

    @SerializedName(value = "childIdx") var childIdx: Int,
    @SerializedName(value = "date") var date: String,
    @SerializedName(value = "time") var time: String,
    @SerializedName(value = "place") var place: Int,
    @SerializedName(value = "detail") var detail: Int,
    @SerializedName(value = "etc") var etc: Int,
    @SerializedName(value = "type") var type: Int,
    )
