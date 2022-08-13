package com.umc.save.Record.Auth.Recording

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecordingRetrofitInterfaces {
    @POST("/recording")
    fun record (@Body recording: Recording): Call<RecordingPostResponse>
}