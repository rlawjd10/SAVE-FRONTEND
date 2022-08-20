package com.umc.save.Home.option


interface UserOutView {
    fun onUserOutSuccess(code : Int, result : UserOut)
    fun onUserOutFailure(code : Int, message : String)
}