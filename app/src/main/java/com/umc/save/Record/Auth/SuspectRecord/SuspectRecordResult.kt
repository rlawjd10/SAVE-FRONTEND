package com.umc.save.Record.Auth.SuspectRecord

interface SuspectRecordResult {
    fun recordSuccess(result : Result)
    fun recordFailure()
}