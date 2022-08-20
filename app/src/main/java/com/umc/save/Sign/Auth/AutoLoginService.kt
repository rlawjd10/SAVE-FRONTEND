package com.umc.save.Sign.Auth

import android.content.Context
import android.util.Log
import com.umc.save.Home.option.UserInfoResponse
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutoLoginService {
    private lateinit var autoLoginView: AutoLoginView

    fun setAutoLoginView(autoLoginView: AutoLoginView) {
        this.autoLoginView = autoLoginView
    }

    fun getAutologin(jwt : String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.getAutologin(jwt).enqueue(object : Callback<AutoLoginResponse> {
            override fun onResponse(
                call: Call<AutoLoginResponse>,
                response: Response<AutoLoginResponse>,
            ) {
                Log.d("AUTO-LOGIN SUCCESS",response.toString())
                val resp : AutoLoginResponse = response.body()!!
                when(resp.code) {
                    1000 ->  autoLoginView.onAutoLoginSuccess(resp.code, resp.result)
                    2301 -> autoLoginView.userAutoNotExist(resp.code, resp.message)
                    else -> autoLoginView.onAutoLoginFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<AutoLoginResponse>, t: Throwable) {
                Log.d("AUTO-LOGIN FAILURE",t.message.toString())
            }

        })
    }
}
