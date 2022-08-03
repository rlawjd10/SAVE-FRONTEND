package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*

data class Suspect (
    @SerializedName(value = "suspectIdx") var suspectIdx: Int,
    @SerializedName(value = "suspectName") var suspectName: String? = "",
    @SerializedName(value = "suspectGender") var suspectGender: String,
    @SerializedName(value = "suspectAge") var suspectAge: String,
    @SerializedName(value = "suspectAddress") var suspectAddress: String? = "",
    var suspectDetailAddress: String? = "",
    var relationship: String,
    var createAt: Date
        )
