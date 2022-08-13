package com.umc.save.Record.Auth.ChildGet

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//
//class recyclerVier_childIdx{
//    object childIdx{
//        var childIdx : Int = 0
//    }
//}

class ChildGetService {

    private lateinit var childGetResult : ChildGetResult

    fun setChildGetResult(childGetResult: ChildGetResult){
        this.childGetResult = childGetResult
    }

    fun getChild(userIdx : Int) {
        val childGetService = getRetrofit().create(ChildGetRetrofitInterfaces::class.java)

        childGetService.getChild(userIdx).enqueue(object : Callback<ChildGetResponse> {
            override fun onResponse(call: Call<ChildGetResponse>, response: Response<ChildGetResponse>,) {
                Log.d("CHILDREN-GET SUCCESS",response.toString())
                val resp : ChildGetResponse = response.body()!!
                when(resp.code) {
//                    1000 ->
                    1000 -> childGetResult.getChildSuccess(resp.code, resp.result!!)
                    else -> childGetResult.getChildFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<ChildGetResponse>, t: Throwable) {
                Log.d("CHILDREN-GET FAILURE",t.message.toString())
            }
        })
    }
}