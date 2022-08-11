package com.umc.save.Record.Auth.ChildGet

import com.umc.save.Record.Auth.ChildRecord.ChildRecordResponse
import com.umc.save.Record.Auth.ChildGet.Child
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChildGetRetrofitInterfaces {
        @GET("/info/child/{userIdx}")
        fun getChild (@Path("userIdx") userIdx : Int): Call<ChildGetResponse>

}