package com.umc.save.Locker

data class RecordDetailData (
        var abuseType : String,
        var detailDescription : String? = "",
        var videoIdx : Int? = null,
        var recordingIdx : Int? = null,
        var pictureIdx : Int? = null
        )