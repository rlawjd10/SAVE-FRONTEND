package com.umc.save.Record

import com.google.gson.annotations.SerializedName
import java.util.*

data class Child (
    @SerializedName(value = "userIdx") var userIdx: Int,
    @SerializedName(value = "name") var childName: String,
    @SerializedName(value = "isCertain") var isCertain: Boolean,
    @SerializedName(value = "gender") var childGender: String,
    @SerializedName(value = "age") var childAge: String,
    @SerializedName(value = "address") var childAddress: String,
    @SerializedName(value = "detailAddress") var childDetailAddress: String,
)
