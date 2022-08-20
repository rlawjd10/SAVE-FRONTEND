package com.umc.save.Sign.Auth

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogoutService {
    private lateinit var getLogoutView: getLogoutView

    fun setLogoutView(getLogoutView: getLogoutView) {
        this.getLogoutView = getLogoutView
    }

    fun getLogoutView(jwt : String, userIdx: Int) {
        val getLogoutService = getRetrofit().create(AuthRetrofitInterface::class.java)

        getLogoutService.getLogout(jwt, userIdx).enqueue(object : Callback<getLogoutResponse> {
            override fun onResponse(
                call: Call<getLogoutResponse>,
                response: Response<getLogoutResponse>,
            ) {
                Log.d("LOGOUT RESPONSE",response.toString())
                val resp : getLogoutResponse = response.body()!!
                when(resp.code) {
                    1000 -> getLogoutView.onLogoutSuccess(resp.code, resp.result)
                    else -> getLogoutView.onLogoutFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<getLogoutResponse>, t: Throwable) {
                Log.d("LOGOUT FAILURE",t.message.toString())
            }

        })
    }
}