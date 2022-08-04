package com.umc.save.Locker

import android.util.Log
import com.umc.save.ChildrenRetrofitInterfaces
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildrenService {
    private lateinit var childrenView : ChildrenView

    fun setChildrenView(childrenView : ChildrenView) {
        this.childrenView = childrenView
    }

    fun getChildren(userIdx : Int) {
        val childrenService = getRetrofit().create(ChildrenRetrofitInterfaces::class.java)

        childrenService.getChildren(userIdx).enqueue(object : Callback<ChildrenResponse> {
            override fun onResponse(call: Call<ChildrenResponse>, response: Response<ChildrenResponse> ,) {
               Log.d("CHILDREN-GET SUCCESS",response.toString())
                val resp : ChildrenResponse = response.body()!!
                when(resp.code) {
                    1000 -> childrenView.onGetChildSuccess(resp.code, resp.result!!)
                    2301 -> childrenView.childNotExist(resp.code, resp.message)
                    else -> childrenView.onGetChildFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<ChildrenResponse>, t: Throwable) {
                Log.d("CHILDREN-GET FAILURE",t.message.toString())
            }
        })
    }

}