package com.umc.save.Record.Auth.ChildGet

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChildGetResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : ArrayList<Child>
)
