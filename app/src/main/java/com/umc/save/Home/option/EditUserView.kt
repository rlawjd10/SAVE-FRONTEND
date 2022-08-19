package com.umc.save.Home.option

interface EditUserView {
    fun onEditUserSuccess(code: Int, result: mResult)
    fun EditNotExist(code : Int, message : String)
    fun onEditUserFailure(code : Int, message : String)
}