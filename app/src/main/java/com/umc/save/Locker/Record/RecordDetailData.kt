package com.umc.save.Locker.Record

import com.google.gson.annotations.SerializedName
import com.umc.save.Locker.data.Offender
import com.umc.save.Locker.data.Picture
import com.umc.save.Locker.data.Recording
import com.umc.save.Locker.data.Video

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