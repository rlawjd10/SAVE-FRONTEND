package com.umc.save.Record.Auth.ChildRecord

interface ChildRecordResult {
    fun recordSuccess(result : Result)
    fun NeedUserIdx(code : Int, message : String)
    fun NeedChildName(code : Int, message : String)
    fun NeedChildGender(code : Int, message : String)
    fun NeedChildAge(code : Int, message : String)
    fun NeedChildAddress(code : Int, message : String)
    fun UserDontExist(code : Int, message : String)
    fun recordFailure()
}