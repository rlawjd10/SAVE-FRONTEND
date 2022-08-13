package com.umc.save.Record.Auth.Video

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface VideoRetrofitInterfaces {
    @POST("/video")
    fun record (@Body video : Video): Call<VideoPostResponse>
}