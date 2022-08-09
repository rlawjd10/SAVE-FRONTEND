package com.umc.save.Record

import com.google.gson.annotations.SerializedName

data class Suspect (
    @SerializedName(value = "childIdx") var childIdx: Int,
    @SerializedName(value = "suspectName") var suspectName: String? = "",
    @SerializedName(value = "suspectGender") var suspectGender: String,
    @SerializedName(value = "suspectAge") var suspectAge: String,
    @SerializedName(value = "suspectAddress") var suspectAddress: String? = "",
    @SerializedName(value = "suspectDetailAddress") var suspectDetailAddress: String? = "",
    @SerializedName(value = "relationWithChild") var relationship: String,
    @SerializedName(value = "suspectEtc") var suspectEct: String? = "",
    var isSelected: Boolean = false
)