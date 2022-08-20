package com.umc.save.Sign.Auth

interface getLogoutView {
    fun onLogoutSuccess(code : Int, result : String)
    fun onLogoutFailure(code : Int, message : String)
}