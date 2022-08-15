package com.umc.save.Record.Auth.Video

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface VideoRetrofitInterfaces {
    @Multipart
    @POST("/video")
    fun sendVideo(
        @Part video: ArrayList<MultipartBody.Part>,
        @Part("postVideoReq") postVideoReq : RequestBody
    ) : Call<VideoPostResponse>
}