package com.umc.save.Locker.DeleteChild

import android.util.Log
import com.umc.save.Locker.Children.ChildrenRetrofitInterfaces
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteChildService {
    private lateinit var deleteChildView: DeleteChildView

    fun setDeleteChildView(deleteChildView : DeleteChildView) {
        this.deleteChildView = deleteChildView
    }

    fun deleteChild(childIdx : Int) {
        val deleteChildService = getRetrofit().create(ChildrenRetrofitInterfaces::class.java)

        deleteChildService.deleteChild(childIdx).enqueue(object : Callback<DeleteChildResponse> {
            override fun onResponse(call: Call<DeleteChildResponse>, response: Response<DeleteChildResponse>,) {
               Log.d("CHILD-DELETE SUCCESS",response.toString())
                val resp : DeleteChildResponse = response.body()!!
                when(resp.code) {
                    1000 -> deleteChildView.onDeleteChildSuccess(resp.code, resp.result!!)
                }
            }

            override fun onFailure(call: Call<DeleteChildResponse>, t: Throwable) {
                Log.d("CHILD-DELETE FAILURE",t.message.toString())
            }
        })
    }

}