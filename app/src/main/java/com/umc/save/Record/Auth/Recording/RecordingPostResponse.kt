package com.umc.save.Record.Auth.Recording

import com.google.gson.annotations.SerializedName

data class RecordingPostResponse (
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "recordingIdx") var recordingIdx : ArrayList<Long>,
    @SerializedName(value =  "completeMessage") var completeMessage : String
)