package com.umc.save.Record.Auth.ChildRecord

import com.umc.save.Record.Child
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChildRecordRetrofitInterfaces {
    @POST("/child")
    fun record (@Body child:Child): Call<ChildRecordResponse>
}