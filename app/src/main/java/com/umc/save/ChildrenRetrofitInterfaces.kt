package com.umc.save

import com.umc.save.Locker.ChildrenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChildrenRetrofitInterfaces {

    @GET("/info/child/{userIdx}")
    fun getChildren(@Path("userIdx") userIdx : Int) : Call<ChildrenResponse>

}


