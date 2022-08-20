package com.umc.save.Home.option

import android.util.Log
import com.umc.save.Sign.User.UserRetrofitInterface
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//회원 비밀번호 수정
class PwChangeService {
    private lateinit var pwChangeView: PwChangeView

    fun setPwChangeView(pwChangeView: PwChangeView) {
        this.pwChangeView = pwChangeView
    }

    fun getPwChange(jwt: String, userIdx: Int, pwChange: PwChange) {
        val pwChangeService = getRetrofit().create(UserRetrofitInterface::class.java)

        pwChangeService.getPwChange(jwt, userIdx, pwChange).enqueue(object :
            Callback<PwChangeResponse> {
            override fun onResponse(
                call: Call<PwChangeResponse>,
                response: Response<PwChangeResponse>,
            ) {
                Log.d("PASSWORD CHANGE SUCCESS",response.toString())
                val resp : PwChangeResponse = response.body()!!
                when(resp.code) {
                    1000 -> pwChangeView.onPwChangeSuccess(resp.code, resp.result)
                    2301 -> pwChangeView.userNotExist(resp.code, resp.message)
                    else -> pwChangeView.onPwChangFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwChangeResponse>, t: Throwable) {
                Log.d("PASSWORD CHANGE FAILURE",t.message.toString())
            }

        })
    }
}