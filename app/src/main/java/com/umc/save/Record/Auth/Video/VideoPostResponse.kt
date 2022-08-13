package com.umc.save.Record.Auth.Video

import com.google.gson.annotations.SerializedName

data class VideoPostResponse (
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "videoIdx") var videoIdx : ArrayList<Long>,
    @SerializedName(value =  "completeMessage") var completeMessage : String
)