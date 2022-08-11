package com.umc.save.Record.Auth.SuspectGet

import com.google.gson.annotations.SerializedName

data class Suspect (
    @SerializedName(value = "suspectIdx") var suspectIdx: Int,
    @SerializedName(value = "name") var suspectName: String? = "",
    @SerializedName(value = "gender") var suspectGender: String,
    @SerializedName(value = "age") var suspectAge: String,
    @SerializedName(value = "address") var suspectAddress: String? = "",
    @SerializedName(value = "detailAddress") var suspectDetailAddress: String? = "",
    @SerializedName(value = "relation") var relationship: String,
    var isSelected: Boolean = false
)