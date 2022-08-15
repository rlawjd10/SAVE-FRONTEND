package com.umc.save.Record.Auth.Picture


interface PictureResult {
    fun postPictureSuccess(code: Int, result: Result)
    fun postPictureFailure(code : Int, message : String)
}