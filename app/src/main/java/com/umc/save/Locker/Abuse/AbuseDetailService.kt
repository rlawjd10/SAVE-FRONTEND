package com.umc.save.Locker.Abuse

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbuseDetailService {
    private lateinit var abuseDetailView: AbuseDetailView

    fun setAbuseDetailView(abuseDetailView : AbuseDetailView) {
        this.abuseDetailView = abuseDetailView
    }

    fun getAbuseDetailCase(abuseIdx : Int) {
        val abuseDetailServiceService = getRetrofit().create(AbuseCasesRetrofitInterfaces::class.java)

        abuseDetailServiceService.getAbuseDetailCase(abuseIdx).enqueue(object : Callback<AbuseDetailResponse> {
            override fun onResponse(call: Call<AbuseDetailResponse>, response: Response<AbuseDetailResponse>,) {
               Log.d("ABUSE-DETAIL-GET SUCCESS",response.toString())
                val resp : AbuseDetailResponse = response.body()!!
                when(resp.code) {
                    1000 -> abuseDetailView.onGetAbuseDetailSuccess(resp.code, resp.result)
                    2301 -> abuseDetailView.abuseDetailNotExist(resp.code, resp.message)
                    else -> abuseDetailView.onGetAbuseDetailFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<AbuseDetailResponse>, t: Throwable) {
                Log.d("ABUSE-DETAIL-GET FAILURE",t.message.toString())
            }
        })
    }

}