package com.umc.save.Locker.Abuse

import com.umc.save.Locker.Record.RecordData

interface AbuseView {
    fun onGetAbuseSuccess(code : Int, result : ArrayList<RecordData>)
    fun abuseNotExist(code : Int, message : String)
    fun onGetAbuseFailure(code : Int, message : String)
}