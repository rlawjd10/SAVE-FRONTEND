package com.umc.save.Record.Auth.AbuseSituation


interface AbuseResult {
    fun recordSuccess(result : Result)
    fun recordFailure()
}