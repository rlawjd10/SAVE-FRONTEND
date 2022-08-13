package com.umc.save.Record.Auth.Picture

import java.util.ArrayList

interface PictureResult {
    fun postPictureSuccess(code: Int, result: Result)
    fun postPictureFailure(code : Int, message : String)
}