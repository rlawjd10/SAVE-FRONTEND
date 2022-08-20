package com.umc.save.Home.option

interface PwChangeView {
    fun onPwChangeSuccess(code : Int, result : onpwChange)
    fun userNotExist(code : Int, message : String)
    fun onPwChangFailure(code : Int, message : String)
}