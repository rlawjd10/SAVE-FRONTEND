package com.umc.save.Record.Auth.Video

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoService {
    private lateinit var result : Result
    private lateinit var videoResult: VideoResult

    fun setVideodResult(videoResult: VideoResult){
        this.videoResult = videoResult
    }

    fun postPicture(record: Video){

        val authService = getRetrofit().create(VideoRetrofitInterfaces::class.java)

        authService.record(record).enqueue(object: Callback<VideoPostResponse> {
            override fun onResponse(call: Call<VideoPostResponse>, response: Response<VideoPostResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: VideoPostResponse = response.body()!!

                result = resp.result!!

                when(resp.code){
                    1000 -> {
                        videoResult.postVideoSuccess(resp.code, result)
                    }
                    else -> videoResult.postVideoFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<VideoPostResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}