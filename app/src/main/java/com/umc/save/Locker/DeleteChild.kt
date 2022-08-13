package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*

data class DeleteChild(
    @SerializedName(value = "name")var childName: String,
    @SerializedName(value = "gender") var childGender: String,
    @SerializedName(value = "age") var childAge: String,
    @SerializedName(value = "address") var childAddress: String,
    @SerializedName(value = "detailAddress") var childDetailAddress: String? = "",
    @SerializedName(value = "deleteChild") var childDeleteMessage: String? = ""
)