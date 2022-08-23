package com.umc.save.Locker.Suspect

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuspectsService {
    private lateinit var suspectsView : SuspectsView

    fun setSuspectsView(suspectsView : SuspectsView) {
        this.suspectsView = suspectsView
    }

    fun getSuspects(childIdx : Int) {
        val suspectsService = getRetrofit().create(SuspectsRetrofitInterfaces::class.java)

        suspectsService.getSuspects(childIdx).enqueue(object : Callback<SuspectsResponse> {
            override fun onResponse(call: Call<SuspectsResponse>, response: Response<SuspectsResponse> ) {
                Log.d("SUSPECTS-GET SUCCESS",response.toString())
                val resp : SuspectsResponse = response.body()!!
                when(resp.code) {
                    1000 -> suspectsView.onGetSuspectsSuccess(resp.code, resp.result!!)
                    2301 -> suspectsView.suspectNotExist(resp.code, resp.message)
                    else -> suspectsView.onGetSuspectsFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<SuspectsResponse>, t: Throwable) {
                Log.d("SUSPECTS-GET FAILURE",t.message.toString())
            }
        })

    }

}