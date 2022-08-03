package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Child(
    @SerializedName(value = "childIdx") var childIdx: Int,
    @SerializedName(value = "name")var childName: String,
    var isCertain: Boolean,
    @SerializedName(value = "gender") var childGender: String,
    @SerializedName(value = "age") var childAge: String,
    @SerializedName(value = "address") var childAddress: String,
    var childDetailAddress: String? = "",
    var createAt: Date?
    )