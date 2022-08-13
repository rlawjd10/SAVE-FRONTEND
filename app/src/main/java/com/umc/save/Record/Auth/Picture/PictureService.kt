package com.umc.save.Record.Auth.Picture

import android.util.Log
import com.umc.save.Record.Auth.ChildRecord.ChildRecordResponse
import com.umc.save.getRetrofit
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureService {
    private lateinit var result : Result
    private lateinit var pictureResult : PictureResult

    fun setPicturedResult(pictureResult: PictureResult){
        this.pictureResult = pictureResult
    }

    fun postPicture(imageList : List<MultipartBody.Part?>, pictureData : RequestBody){

        val authService = getRetrofit().create(PictureRetrofitInterfaces::class.java)

        authService.sendPicture(imageList, pictureData).enqueue(object: Callback<PicturePostResponse> {
            override fun onResponse(call: Call<PicturePostResponse>, response: Response<PicturePostResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: PicturePostResponse = response.body()!!
                if(resp != null) {
                    result = resp.result!!

                    when (resp.code) {
                        1000 -> {
                            pictureResult.postPictureSuccess(resp.code, result)
                        }
                        else -> pictureResult.postPictureFailure(resp.code, resp.message)
                    }
                }
                else
                    Log.d("RECORD/FAILURE ================== ", "오류 발생 reponse null")

            }
            override fun onFailure(call: Call<PicturePostResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}