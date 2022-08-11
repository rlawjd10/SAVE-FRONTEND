package com.umc.save.Record.Auth.AbuseSituation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AbuseRetrofitInterfaces {
    @POST("/abuse")
    fun record (@Body abuseSituation: AbuseSituation): Call<AbuseResponse>
}