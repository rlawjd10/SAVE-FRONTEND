package com.umc.save.Locker

data class Recording (
    var recordingIdx : Int,
    var location : String,
    var recordingTitle : String,
    var second : Int = 0,
    var length : Int,
    var progress: Int = 0,
    var isPlaying: Boolean = false
)