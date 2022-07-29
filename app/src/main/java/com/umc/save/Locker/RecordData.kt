package com.umc.save.Locker

import java.sql.Time
import java.util.*

data class RecordData (
    var abuseIdx : Int,
    var abuseDate : Date,
    var abuseTime : Time,
    var abusePlace : String,
    var createAt : Date
    )
