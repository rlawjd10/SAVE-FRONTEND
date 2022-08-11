package com.umc.save.Locker

import com.google.gson.annotations.SerializedName

data class Picture (
    @SerializedName(value = "picturePath") var location : String? =""
)