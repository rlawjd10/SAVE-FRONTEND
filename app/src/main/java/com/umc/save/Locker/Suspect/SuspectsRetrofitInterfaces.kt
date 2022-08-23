package com.umc.save.Locker.Suspect

import com.umc.save.Locker.DeleteSuspect.DeleteSuspectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SuspectsRetrofitInterfaces {

    @GET("/info/suspect/{childIdx}")
    fun getSuspects(@Path("childIdx") childIdx : Int) : Call<SuspectsResponse>

    @PATCH("/suspect/status/{suspectIdx}")
    fun deleteSuspect(@Path("suspectIdx") suspectIdx : Int) : Call<DeleteSuspectResponse>

}


