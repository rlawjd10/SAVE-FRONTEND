package com.umc.save.Locker.Suspect

import com.google.gson.annotations.SerializedName
import com.umc.save.Locker.data.Suspect
import kotlin.collections.ArrayList

data class SuspectsResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : ArrayList<Suspect>?
)

