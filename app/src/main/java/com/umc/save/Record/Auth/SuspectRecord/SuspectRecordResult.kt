package com.umc.save.Record.Auth.SuspectRecord

interface SuspectRecordResult {
    fun recordSuccess(result: Result)
    fun NeedChildIdx(code : Int, message : String)
    fun NeedChildGender(code : Int, message : String)
    fun NeedChildAge(code : Int, message : String)
    fun NeedRelation(code : Int, message : String)
    fun ChildDontExist(code : Int, message : String)
    fun recordFailure()
}