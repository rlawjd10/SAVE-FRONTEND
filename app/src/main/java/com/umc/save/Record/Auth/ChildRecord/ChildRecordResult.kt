package com.umc.save.Record.Auth.ChildRecord

interface ChildRecordResult {
    fun recordSuccess(result : Result)
    fun recordFailure()
}