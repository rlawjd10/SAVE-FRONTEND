package com.umc.save.Record.Auth.Video


interface VideoResult {
    fun postVideoSuccess(code: Int, result: Result)
    fun postVideoFailure(code : Int, message : String)
}