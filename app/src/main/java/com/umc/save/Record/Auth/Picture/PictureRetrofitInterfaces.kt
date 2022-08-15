package com.umc.save.Record.Auth.Picture

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PictureRetrofitInterfaces {
    @Multipart
    @POST("/picture")
    fun sendPicture(
        @Part picture: ArrayList<MultipartBody.Part>,
        @Part("postPictureReq") postPictureReq : RequestBody
    ) : Call<PicturePostResponse>
}