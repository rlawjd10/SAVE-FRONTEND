package com.umc.save.Record

import java.sql.Time
import java.sql.Timestamp
import java.util.*

data class AbuseSituation (
    var abuseIdx: Int,
    var childIdx: Int,
    var abuseDate : Date,
    var abuseTime : Time,
    var abusePlace : String,
    var abuseType : String,
    var detailDescription : String? = "",
    var ect : String? = "",
    var createAt : Date,
    var editedDate : Timestamp
)