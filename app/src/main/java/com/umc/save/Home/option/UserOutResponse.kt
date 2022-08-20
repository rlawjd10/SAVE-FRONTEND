package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName

data class UserOutResponse(
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : UserOut
)
data class UserOut (
    @SerializedName(value = "name") val name : String,
    @SerializedName(value = "phone") val phone : String,
    @SerializedName(value = "email") val email : String,
    @SerializedName(value = "completeMessage") val completeMessage : String
   )
