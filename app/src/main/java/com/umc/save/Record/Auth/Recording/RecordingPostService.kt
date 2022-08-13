package com.umc.save.Record.Auth.Recording

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordingPostService {
    private lateinit var result : Result
    private lateinit var recordingResult : RecordingResult

    fun setRecordingResult(recordingResult: RecordingResult){
        this.recordingResult = recordingResult
    }

    fun postRecording(record: Recording){

        val authService = getRetrofit().create(RecordingRetrofitInterfaces::class.java)

        authService.record(record).enqueue(object: Callback<RecordingPostResponse> {
            override fun onResponse(call: Call<RecordingPostResponse>, response: Response<RecordingPostResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: RecordingPostResponse = response.body()!!

                result = resp.result!!

                when(resp.code){
                    1000 -> {
                        recordingResult.postRecordingSuccess(resp.code, result)
                    }
                    else -> recordingResult.postRecordingFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<RecordingPostResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}