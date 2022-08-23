package com.umc.save.Locker.DeleteRecord

interface DeleteRecordView {
    fun onDeleteRecordSuccess(code : Int, result : DeleteRecord)
    fun onDeleteRecordFailure(code : Int, message : String)

}