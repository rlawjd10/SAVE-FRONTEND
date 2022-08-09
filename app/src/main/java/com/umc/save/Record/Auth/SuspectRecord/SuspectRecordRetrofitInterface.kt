package com.umc.save.Record.Auth.SuspectRecord

import com.umc.save.Record.Suspect
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SuspectRecordRetrofitInterface {
    @POST("/suspect")
    fun record (@Body suspect: Suspect): Call<SuspectRecordResponse>
}