package com.umc.save.Locker.data

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Child(
    @SerializedName(value = "childIdx") var childIdx: Int,
    @SerializedName(value = "name")var childName: String,
    @SerializedName(value = "gender") var childGender: String,
    @SerializedName(value = "age") var childAge: String,
    @SerializedName(value = "address") var childAddress: String,
    @SerializedName(value = "detailAddress") var childDetailAddress: String? = "",
    @SerializedName(value = "createAt") var createAt: Date,
    var isClicked : Boolean = false
    )