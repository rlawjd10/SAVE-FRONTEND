package com.umc.save.Locker.data

import com.google.gson.annotations.SerializedName

data class Picture (
    @SerializedName(value = "picturePath") var location : String? =""
)