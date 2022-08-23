package com.umc.save.Locker.DeleteSuspect

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class DeleteSuspectResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : String
)
