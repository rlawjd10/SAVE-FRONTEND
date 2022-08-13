package com.umc.save.Record.Auth.SuspectGet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SuspectGetRetrofitInterfaces {
    @GET("/info/suspect/{childIdx}")
    fun getSuspect (@Path("childIdx") childIdx : Int): Call<SuspectGetResponse>
}