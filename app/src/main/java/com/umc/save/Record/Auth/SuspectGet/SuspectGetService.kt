package com.umc.save.Record.Auth.SuspectGet

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuspectGetService {
    private lateinit var suspectGetResult : SuspectGetResult

    fun setSuspectGetResult(suspectGetResult: SuspectGetResult){
        this.suspectGetResult = suspectGetResult
    }

    fun getSuspect(childIdx : Int) {
        val suspectGetService = getRetrofit().create(SuspectGetRetrofitInterfaces::class.java)

        suspectGetService.getSuspect(childIdx).enqueue(object : Callback<SuspectGetResponse> {
            override fun onResponse(call: Call<SuspectGetResponse>, response: Response<SuspectGetResponse>,) {
                Log.d("CHILDREN-GET SUCCESS",response.toString())
                val resp : SuspectGetResponse = response.body()!!
                when(resp.code) {
                    1000 -> suspectGetResult.getSuspectSuccess(resp.code, resp.result!!)
                    else -> suspectGetResult.getSuspectFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<SuspectGetResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}