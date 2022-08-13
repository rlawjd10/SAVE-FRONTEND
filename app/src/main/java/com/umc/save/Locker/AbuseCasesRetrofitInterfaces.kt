package com.umc.save.Locker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AbuseCasesRetrofitInterfaces {

    @GET("/info/abuse/{childIdx}")
    fun getAbuseCases(@Path("childIdx") childIdx : Int) : Call<AbuseResponse>

    @GET("/abuse/{abuseIdx}")
    fun getAbuseDetailCase(@Path("abuseIdx") abuseIdx : Int) : Call<AbuseDetailResponse>

    @PATCH("/abuse/status/{abuseIdx}")
    fun deleteRecord(@Path("abuseIdx") abuseIdx : Int) : Call<DeleteRecordResponse>


}


