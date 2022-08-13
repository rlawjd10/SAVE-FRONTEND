package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class DeleteChildResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : DeleteChild?
)
