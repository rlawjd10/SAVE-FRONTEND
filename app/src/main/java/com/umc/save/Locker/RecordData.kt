package com.umc.save.Locker

import java.sql.Time
import java.util.*

data class RecordData (
    var childIdx: Int,
    var abuseDate : Date,
    var abuseTime : Time,
    var abusePlace : String,
    var abuseType : String,
    var createAt : Date
    )
