package com.umc.save.Locker

interface AbuseDetailView {
    fun onGetAbuseDetailSuccess(code : Int, result : RecordDetailData)
    fun abuseDetailNotExist(code : Int, message : String)
    fun onGetAbuseDetailFailure(code : Int, message : String)
}