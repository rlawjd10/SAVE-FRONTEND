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
                    6000 -> recordResult.NeedUserIdx(resp.code, resp.message)
                    6001 -> recordResult.NeedChildName(resp.code, resp.message)
                    6002 -> recordResult.NeedChildGender(resp.code, resp.message)
                    6003 -> recordResult.NeedChildAge(resp.code, resp.message)
                    6004-> recordResult.NeedChildAddress(resp.code, resp.message)
                    6006 -> recordResult.UserDontExist(resp.code, resp.message)
                    else -> recordResult.recordFailure()
                }
            }
            override fun onFailure(call: Call<ChildRecordResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}