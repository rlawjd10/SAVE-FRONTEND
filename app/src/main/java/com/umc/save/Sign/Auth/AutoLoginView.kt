package com.umc.save.Sign.Auth

import com.umc.save.Home.option.UserInfo

interface AutoLoginView {
    fun onAutoLoginSuccess(code : Int, result : aResult)
    fun userAutoNotExist(code : Int, message : String)
    fun onAutoLoginFailure(code : Int, message : String)
}