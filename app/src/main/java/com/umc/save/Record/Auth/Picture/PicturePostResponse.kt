package com.umc.save.Record.Auth.Picture

import com.google.gson.annotations.SerializedName

data class PicturePostResponse (
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value =  "pictureIdx") var pictureIdx : ArrayList<Long>,
    @SerializedName(value =  "completeMessage") var completeMessage : String
)