package com.umc.save.Locker.Children

import com.google.gson.annotations.SerializedName
import com.umc.save.Locker.data.Child
import kotlin.collections.ArrayList

data class ChildrenResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : ArrayList<Child>?
)
