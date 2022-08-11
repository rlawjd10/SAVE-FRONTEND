package com.umc.save.Locker

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbuseService {
    private lateinit var abuseView : AbuseView

    fun setAbuseView(abuseView : AbuseView) {
        this.abuseView = abuseView
    }

    fun getAbuseCases(childIdx : Int) {
        val abuseService = getRetrofit().create(AbuseCasesRetrofitInterfaces::class.java)

        abuseService.getAbuseCases(childIdx).enqueue(object : Callback<AbuseResponse> {
            override fun onResponse(call: Call<AbuseResponse>, response: Response<AbuseResponse>,) {
               Log.d("ABUSE-GET SUCCESS",response.toString())
                val resp : AbuseResponse = response.body()!!
                when(resp.code) {
                    1000 -> abuseView.onGetAbuseSuccess(resp.code, resp.result!!)
                    2301 -> abuseView.abuseNotExist(resp.code, resp.message)
                    else -> abuseView.onGetAbuseFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<AbuseResponse>, t: Throwable) {
                Log.d("ABUSE-GET FAILURE",t.message.toString())
            }
        })
    }

}