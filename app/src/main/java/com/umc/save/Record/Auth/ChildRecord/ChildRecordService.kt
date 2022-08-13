package com.umc.save.Record.Auth.ChildRecord

import android.util.Log
import com.umc.save.Record.Child
import com.umc.save.databinding.ActivityOffenderRecordBinding
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class childidx_var {
    object childIdx {
        var childIdx : Int = 0
    }
}

class ChildRecordService {

    private var result : Result = Result(childIdx = 1)
    private lateinit var recordResult : ChildRecordResult

    fun setRecordResult(recordResult: ChildRecordResult){
        this.recordResult = recordResult
    }

    fun record(record: Child){

        val authService = getRetrofit().create(ChildRecordRetrofitInterfaces::class.java)

        authService.record(record).enqueue(object: Callback<ChildRecordResponse> {
            override fun onResponse(call: Call<ChildRecordResponse>, response: Response<ChildRecordResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: ChildRecordResponse = response.body()!!

                result = resp.result!!

                when(resp.code){
                    1000 -> {
                        recordResult.recordSuccess(result)
                    }
                    else -> recordResult.recordFailure()
                }
            }
            override fun onFailure(call: Call<ChildRecordResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}