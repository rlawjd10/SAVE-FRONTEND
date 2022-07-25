package com.umc.save.Locker

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Child(
    var childIdx: Int,
    var childName: String,
    var isCertain: Boolean,
    var childGender: String,
    var childAge: String,
    var childAddress: String,
    var childDetailAddress: String? = "",
    var createAt: Date
    )