package com.umc.save.Locker

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteRecordService {
    private lateinit var deleteRecordView: DeleteRecordView

    fun setDeleteRecordView(deleteRecordView : DeleteRecordView) {
        this.deleteRecordView = deleteRecordView
    }

    fun deleteRecord(abuseIdx : Int) {
        val deleteRecordService = getRetrofit().create(AbuseCasesRetrofitInterfaces::class.java)

        deleteRecordService.deleteRecord(abuseIdx).enqueue(object : Callback<DeleteRecordResponse> {
            override fun onResponse(call: Call<DeleteRecordResponse>, response: Response<DeleteRecordResponse>,) {
               Log.d("ABUSE-DELETE SUCCESS",response.toString())
                val resp : DeleteRecordResponse = response.body()!!
                when(resp.code) {
                    1000 -> deleteRecordView.onDeleteRecordSuccess(resp.code, resp.result)
                    2061 -> deleteRecordView.onDeleteRecordFailure(resp.code, resp.message)

                }
            }

            override fun onFailure(call: Call<DeleteRecordResponse>, t: Throwable) {
                Log.d("ABUSE-DELETE FAILURE",t.message.toString())
            }
        })
    }

}