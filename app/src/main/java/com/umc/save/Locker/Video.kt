package com.umc.save.Locker

data class Video (
    var videoIdx : Int,
    var location : String,
    var thumb : String,
//    var isPlaying: Boolean = false,
    var image : Int? = null,
    var video : Int? = null
//var image video 다 지우기 (진짜 서버에서 값을 받으면)
)