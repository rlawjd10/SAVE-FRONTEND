package com.umc.save.Locker

import java.util.*

data class Suspect (
    var suspectIdx: Int,
    var suspectName: String? = "",
    var suspectGender: String,
    var suspectAge: String,
    var suspectAddress: String? = "",
    var suspectDetailAddress: String? = "",
    var relationship: String,
    var createAt: Date
        )
