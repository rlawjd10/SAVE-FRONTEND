package com.umc.save.Record.Auth.Picture

import android.util.Log
import com.umc.save.BASE_URL
import com.umc.save.getRetrofit
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val array1 = ArrayList<Int>()

class PictureService {

    private var result : Result = Result(pictureIdx = arrayListOf(), "")
    private lateinit var pictureResult : PictureResult

    fun setPicturedResult(pictureResult: PictureResult){
        this.pictureResult = pictureResult
    }

    fun sendPicture(picture : ArrayList<MultipartBody.Part>, picAbuseIdx_get : Int, picChildIdx_get : Int){

        val authService = getRetrofit().create(PictureRetrofitInterfaces::class.java)

        val body = RequestBody.create(MultipartBody.FORM, "")
        val emptyPart = MultipartBody.Part.createFormData("pictureList","",body)
        val emptyList = arrayListOf<MultipartBody.Part>()
        emptyList.add(emptyPart)

        var picAbuseIdx = picAbuseIdx_get
        var picChildIdx = picChildIdx_get

        val jsonObject = JSONObject("{\"picAbuseIdx\" :\"${picAbuseIdx}\", \"picChildIdx\" :${picChildIdx}}").toString()
        val postPictureReq = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject)

        Log.d("RECORD/FAILURE ==============================================================================================", picture[0].toString())
        Log.d("RECORD/FAILURE ==============================================================================================", postPictureReq.toString())

        authService.sendPicture(picture, postPictureReq).enqueue(object: Callback<PicturePostResponse> {
            override fun onResponse(call: Call<PicturePostResponse>, response: Response<PicturePostResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: PicturePostResponse = response.body()!!
                Log.d("RECORD/FAILURE =================================================", resp.result!!.pictureIdx.toString())
                Log.d("RECORD/FAILURE =================================================", resp.result!!.completeMessage)

                if(resp != null) {
//                    result = resp.result!!
                    result.pictureIdx.addAll(resp.result!!.pictureIdx)
                    result.completeMessage = resp.result!!.completeMessage

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

//    fun sendPicture(picture : ArrayList<MultipartBody.Part>, picAbuseIdx_get : Int, picChildIdx_get : Int )
//    {
//        Log.d("RECORD/FAILURE ==============================================================================================", "sendPicture 실행")
//
//        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
//        val authService = retrofit.create(PictureRetrofitInterfaces::class.java)
//        val jsonArray = arrayListOf<String>()
//        val body = RequestBody.create(MultipartBody.FORM, "")
//        val emptyPart = MultipartBody.Part.createFormData("pictureList","",body)
//        val emptyList = arrayListOf<MultipartBody.Part>()
//        emptyList.add(emptyPart)
//
//        var picAbuseIdx = picAbuseIdx_get
//        var picChildIdx = picChildIdx_get
//
//        val jsonObject = JSONObject("{\"picAbuseIdx\" :\"${picAbuseIdx}\", \"picChildIdx\" :${picChildIdx}}").toString()
//        val postPictureReq = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject)
//
//        Log.d("RECORD/FAILURE ==============================================================================================", picture[0].toString())
//        Log.d("RECORD/FAILURE ==============================================================================================", postPictureReq.toString())
//
//        authService.sendPicture(picture, postPictureReq).enqueue(object : Callback<PicturePostResponse>{
//            override fun onResponse(call: Call<PicturePostResponse>, response: Response<PicturePostResponse>) {
//                Log.d("RECORD/SUCCESS",response.toString())
//
//                if(response.body() != null) {
//                    val resp: PicturePostResponse = response.body()!!
//                    result = resp.result!!
//
//                    when (resp.code) {
//                        1000 -> {
//                            pictureResult.postPictureSuccess(resp.code, result)
//                        }
//                        else -> pictureResult.postPictureFailure(resp.code, resp.message)
//                    }
//                }
//                else
//                    Log.d("RECORD/FAILURE ================== ", "오류 발생 reponse null")
//
//            }
//            override fun onFailure(call: Call<PicturePostResponse>, t: Throwable) {
//                Log.d("오류 발생",t.message.toString())
//            }
//        })
//    }
}