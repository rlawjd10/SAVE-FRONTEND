package com.umc.save.Record.Auth.Recording


interface RecordingResult {
    fun postRecordingSuccess(code: Int, result: Result)
    fun postRecordingFailure(code : Int, message : String)
}