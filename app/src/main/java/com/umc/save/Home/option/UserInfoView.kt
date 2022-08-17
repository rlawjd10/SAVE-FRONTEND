package com.umc.save.Home.option

interface UserInfoView {
    fun onGetUserSuccess(code : Int, result : UserInfo)
    fun userNotExist(code : Int, message : String)
    fun onGetUserFailure(code : Int, message : String)
}