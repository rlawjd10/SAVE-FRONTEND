package com.umc.save.Record.Auth.Recording


interface RecordingResult {
    fun postRecordingSuccess(code: Int, result: Result)
    fun NeedFile(code : Int, message: String)
    fun postRecordingFailure(code : Int, message : String)
}