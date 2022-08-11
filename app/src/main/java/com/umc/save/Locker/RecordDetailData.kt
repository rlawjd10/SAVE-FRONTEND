package com.umc.save.Locker

import com.google.gson.annotations.SerializedName

data class RecordDetailData (
        @SerializedName(value = "abuseIdx") var abuseIdx : Int,
        @SerializedName(value = "date") var abuseDate : String,
        @SerializedName(value = "time") var abuseTime : String,
        @SerializedName(value = "place") var abusePlace : String,
        @SerializedName(value = "type") var abuseType : String,
        @SerializedName(value = "detail") var detailDescription : String? = "",
        @SerializedName(value = "etc") var detailEtcDescription : String? = "",
        @SerializedName(value = "createAt") var createAt : String,
        @SerializedName(value = "picture") var pictureList : ArrayList<Picture>,
        @SerializedName(value = "video") var videoList : ArrayList<Video>,
        @SerializedName(value = "recording") var recordingList : ArrayList<Recording>,
        @SerializedName(value = "suspect") var suspect : Offender
        )