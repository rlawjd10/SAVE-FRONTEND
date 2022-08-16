package com.umc.save.Record.Auth.Video

import android.util.Log
import com.umc.save.getRetrofit
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoService {
    private var result : Result = Result(videoIdx = arrayListOf(), "")
    private lateinit var videoResult : VideoResult

    fun setVideoResult(videoResult: VideoResult){
        this.videoResult = videoResult
    }

    fun sendVideo(video : ArrayList<MultipartBody.Part>, thumbnail : MultipartBody.Part, vidAbuseIdx_get : Int, vidChildIdx_get : Int){

        val authService = getRetrofit().create(VideoRetrofitInterfaces::class.java)

        val body = RequestBody.create(MultipartBody.FORM, "")
        val emptyPart = MultipartBody.Part.createFormData("videoList","",body)
        val emptyList = arrayListOf<MultipartBody.Part>()
        emptyList.add(emptyPart)

        var vidAbuseIdx = vidAbuseIdx_get
        var vidChildIdx = vidChildIdx_get

        val jsonObject = JSONObject("{\"vidAbuseIdx\" :\"${vidAbuseIdx}\", \"vidChildIdx\" :${vidChildIdx}}").toString()
        val postVideoReq = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject)

        Log.d("RECORD/FAILURE ==============================================================================================", video[0].toString())
        Log.d("RECORD/FAILURE ==============================================================================================", postVideoReq.toString())

        authService.sendVideo(video, thumbnail, postVideoReq).enqueue(object: Callback<VideoPostResponse> {
            override fun onResponse(call: Call<VideoPostResponse>, response: Response<VideoPostResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: VideoPostResponse = response.body()!!
                Log.d("RECORD/FAILURE =================================================", resp.result!!.videoIdx.toString())
                Log.d("RECORD/FAILURE =================================================", resp.result!!.completeMessage)

                if(resp != null) {
//                    result = resp.result!!
                    result.videoIdx.addAll(resp.result!!.videoIdx)
                    result.completeMessage = resp.result!!.completeMessage

                    when (resp.code) {
                        1000 -> {
                            videoResult.postVideoSuccess(resp.code, result)
                        }
                        else -> videoResult.postVideoFailure(resp.code, resp.message)
                    }
                }
                else
                    Log.d("RECORD/FAILURE ================== ", "오류 발생 reponse null")

            }
            override fun onFailure(call: Call<VideoPostResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}