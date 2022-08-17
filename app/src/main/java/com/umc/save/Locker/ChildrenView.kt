package com.umc.save.Locker

import com.umc.save.Sign.Auth.AuthResponse
import com.umc.save.Sign.User.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChildrenView {
    fun onGetChildSuccess(code : Int, result : ArrayList<Child>)
    fun childNotExist(code : Int, message : String)
    fun onGetChildFailure(code : Int, message : String)
}