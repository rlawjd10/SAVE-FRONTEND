package com.umc.save.Home.option

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class UserInfoResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : UserInfo
)
