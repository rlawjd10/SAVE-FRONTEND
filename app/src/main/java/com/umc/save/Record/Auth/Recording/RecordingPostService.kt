package com.umc.save.Record.Auth.Recording

import android.util.Log
import com.umc.save.getRetrofit
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecordingPostService {

    private var result : Result = Result(recordingIdx = arrayListOf(), "")
    private lateinit var recordingResult : RecordingResult

    fun setRecordingResult(recordingResult: RecordingResult){
        this.recordingResult = recordingResult
    }

    fun sendRecording(recording : ArrayList<MultipartBody.Part>, recAbuseIdx_get : Int, recChilIdx_get : Int){

        val authService = getRetrofit().create(RecordingRetrofitInterfaces::class.java)

        val body = RequestBody.create(MultipartBody.FORM, "")
        val emptyPart = MultipartBody.Part.createFormData("recording","",body)
        val emptyList = arrayListOf<MultipartBody.Part>()
        emptyList.add(emptyPart)

        var recAbuseIdx = recAbuseIdx_get
        var recChildIdx = recChilIdx_get

        val jsonObject = JSONObject("{\"recAbuseIdx\" :\"${recAbuseIdx}\", \"recChildIdx\" :${recChildIdx}}").toString()
        val postRecordingReq= RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject)


        Log.d("**RECORD/FAILURE ==============================================================================================", recording[0].toString())
        Log.d("**RECORD/FAILURE ==============================================================================================", postRecordingReq.toString())
        try {
            Log.d(
                "recAbuseIdx ==============================================================================================",
                recAbuseIdx.toString()
            )
            Log.d(
                "recChildIdx ==============================================================================================",
                recChildIdx.toString()
            )
        }catch (e : NullPointerException){
            e.printStackTrace()
        }



        authService.sendRecording(recording, postRecordingReq).enqueue(object: Callback<RecordingPostResponse> {
            override fun onResponse(call: Call<RecordingPostResponse>, response: Response<RecordingPostResponse>) {

                Log.d("RECORD/SUCCESS",response.toString())
                try {
                    val resp: RecordingPostResponse = response.body()!!
                    Log.d(
                        "==RECORD/FAILURE =================================================",
                        resp.result!!.recordingIdx.toString()
                    )
                    Log.d(
                        "==RECORD/FAILURE =================================================",
                        resp.result!!.completeMessage
                    )

                    if (resp != null) {
//                    result = resp.result!!
                        result.recordingIdx.addAll(resp.result!!.recordingIdx)
                        result.completeMessage = resp.result!!.completeMessage

                        when (resp.code) {
                            1000 -> {
                                recordingResult.postRecordingSuccess(resp.code, result)
                            }
                            else -> recordingResult.postRecordingFailure(resp.code, resp.message)
                        }
                    } else
                        Log.d("RECORD/FAILURE ================== ", "오류 발생 reponse null")
                }catch (e : NullPointerException){
                    e.printStackTrace()
                }

            }
            override fun onFailure(call: Call<RecordingPostResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })
    }
}