package com.umc.save.Locker.data

import com.google.gson.annotations.SerializedName

data class Recording (
    @SerializedName(value = "recordingPath") var location : String,
    @SerializedName(value = "recordingName") var recordingTitle : String,
    var isPlaying : Boolean = false
)