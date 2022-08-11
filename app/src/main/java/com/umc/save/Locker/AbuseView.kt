package com.umc.save.Locker

interface AbuseView {
    fun onGetAbuseSuccess(code : Int, result : ArrayList<RecordData>)
    fun abuseNotExist(code : Int, message : String)
    fun onGetAbuseFailure(code : Int, message : String)
}