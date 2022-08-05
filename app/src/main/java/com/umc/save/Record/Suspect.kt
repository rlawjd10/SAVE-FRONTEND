package com.umc.save.Record

import java.util.*

data class Suspect (
    var suspectIdx: Int,
    var suspectName: String? = "",
    var childIdx: Int,
    var suspectGender: String,
    var suspectAge: String,
    var suspectAddress: String? = "",
    var suspectDetailAddress: String? = "",
    var relationship: String,
    var createAt: Date
)