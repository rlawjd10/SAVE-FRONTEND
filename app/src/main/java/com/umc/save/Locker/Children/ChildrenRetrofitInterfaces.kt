package com.umc.save.Locker.Children

import com.umc.save.Locker.DeleteChild.DeleteChildResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChildrenRetrofitInterfaces {

    @GET("/info/child/{userIdx}")
    fun getChildren(@Path("userIdx") userIdx : Int) : Call<ChildrenResponse>

    @PATCH("/child/status/{childIdx}")
    fun deleteChild(@Path("childIdx") childIdx : Int) : Call<DeleteChildResponse>


}


