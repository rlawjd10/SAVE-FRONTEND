package com.umc.save.Locker

interface DeleteRecordView {
    fun onDeleteRecordSuccess(code : Int, result : DeleteRecord)
    fun onDeleteRecordFailure(code : Int, message : String)

}