package com.umc.save

import com.umc.save.Locker.SuspectsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SuspectsRetrofitInterfaces {

    @GET("/info/suspect/{childIdx}")
    fun getSuspects(@Path("childIdx") childIdx : Int) : Call<SuspectsResponse>

}


