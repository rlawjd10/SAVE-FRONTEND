package com.umc.save.Record.Auth.AbuseSituation

import android.util.Log
import com.umc.save.Record.Auth.AbuseSituation.Result
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class abuse_var {
    object abuse {
        var a_date : String = ""
        var a_time : String = ""
        var place : String = ""
        var detail : String = ""
        var etc : String = ""
        var a_type : String = ""

        var abuseIdx : Int = 0
    }
}

class AbuseService {

    private var result : Result = Result(abuseIdx = 1)
    private lateinit var abuseResult : AbuseResult

    fun setRecordResult(abuseResult: AbuseResult){
        this.abuseResult = abuseResult
    }

    fun record(abuseSituation: AbuseSituation){

        val authService = getRetrofit().create(AbuseRetrofitInterfaces::class.java)

        authService.record(abuseSituation).enqueue(object: Callback<AbuseResponse> {
            override fun onResponse(call: Call<AbuseResponse>, response: Response<AbuseResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: AbuseResponse = response.body()!!

                result = resp.result!!

                when(resp.code){
                    1000 -> {
                        abuseResult.recordSuccess(result)
                    }
                    else -> abuseResult.recordFailure()
                }
            }
            override fun onFailure(call: Call<AbuseResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}