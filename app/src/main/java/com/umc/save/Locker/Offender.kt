package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*

//Suspect의 일부분만 필요해서 Offender 생성
data class Offender (
    @SerializedName(value = "suspectName") var suspectName: String? = "",
    @SerializedName(value = "suspectGender") var suspectGender: String,
    @SerializedName(value = "suspectAge") var suspectAge: String,
    @SerializedName(value = "suspectAddress") var suspectAddress: String? = "",
    @SerializedName(value = "detailAddress") var suspectDetailAddress: String? = "",
    @SerializedName(value = "relation") var relationship: String
        )
