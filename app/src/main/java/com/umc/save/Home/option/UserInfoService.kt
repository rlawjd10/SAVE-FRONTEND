package com.umc.save.Home.option

import android.util.Log
import com.umc.save.Sign.User.UserRetrofitInterface
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoService {
    private lateinit var userInfoView : UserInfoView

    fun setUserInfoView(userInfoView : UserInfoView) {
        this.userInfoView = userInfoView
    }

    fun getUserInfo(userIdx : Int) {
        val userInfoService = getRetrofit().create(UserRetrofitInterface::class.java)

        userInfoService.getUserInfo(userIdx).enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>,) {
               Log.d("USER-GET SUCCESS",response.toString())
                val resp : UserInfoResponse = response.body()!!
                when(resp.code) {
                    1000 -> userInfoView.onGetUserSuccess(resp.code, resp.result)
                    2004 -> userInfoView.userNotExist(resp.code, resp.message)
                    4000 -> userInfoView.onGetUserFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Log.d("USER-GET FAILURE",t.message.toString())
            }
        })
    }

}