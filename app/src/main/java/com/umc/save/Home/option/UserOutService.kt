package com.umc.save.Home.option

import android.util.Log
import com.umc.save.Sign.User.UserRetrofitInterface
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserOutService {

    private lateinit var userOutView: UserOutView

    fun setUserOutView(userOutView: UserOutView) {
        this.userOutView = userOutView
    }

    fun getUserOut(userIdx : Int) {
        val userOutService = getRetrofit().create(UserRetrofitInterface::class.java)

        userOutService.outUser(userIdx).enqueue(object : Callback<UserOutResponse> {
            override fun onResponse(
                call: Call<UserOutResponse>,
                response: Response<UserOutResponse>,
            ) {
                Log.d("USER-OUT RESPONSE",response.toString())
                val resp : UserOutResponse = response.body()!!
                when(resp.code) {
                    1000 -> userOutView.onUserOutSuccess(resp.code, resp.result)
                    4010 -> userOutView.onUserOutFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<UserOutResponse>, t: Throwable) {
                Log.d("USER-OUT FAILURE",t.message.toString())
            }

        })
    }
}