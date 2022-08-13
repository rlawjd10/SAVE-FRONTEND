package com.umc.save.Locker

import android.util.Log
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteSuspectService {
    private lateinit var deleteSuspectView: DeleteSuspectView

    fun setDeleteSuspectView(deleteSuspectView : DeleteSuspectView) {
        this.deleteSuspectView = deleteSuspectView
    }

    fun deleteSuspect(suspectIdx : Int) {
        val deleteSuspectService = getRetrofit().create(SuspectsRetrofitInterfaces::class.java)

        deleteSuspectService.deleteSuspect(suspectIdx).enqueue(object : Callback<DeleteSuspectResponse> {
            override fun onResponse(call: Call<DeleteSuspectResponse>, response: Response<DeleteSuspectResponse>,) {
               Log.d("SUSPECT-DELETE SUCCESS",response.toString())
                val resp : DeleteSuspectResponse = response.body()!!
                when(resp.code) {
                    1000 -> deleteSuspectView.onDeleteSuspectSuccess(resp.code, resp.result)
                    2061 -> deleteSuspectView.onDeleteSuspectFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DeleteSuspectResponse>, t: Throwable) {
                Log.d("SUSPECT-DELETE FAILURE",t.message.toString())
            }
        })
    }

}