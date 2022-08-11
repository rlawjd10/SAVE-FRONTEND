package com.umc.save.Record.Auth.AbuseSituation

import com.umc.save.Record.Auth.AbuseSituation.Result

interface AbuseResult {
    fun recordSuccess(result : Result)
    fun recordFailure()
}