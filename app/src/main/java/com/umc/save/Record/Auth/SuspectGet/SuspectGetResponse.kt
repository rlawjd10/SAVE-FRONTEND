package com.umc.save.Record.Auth.SuspectGet

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class SuspectGetResponse (
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : ArrayList<Suspect>
    )