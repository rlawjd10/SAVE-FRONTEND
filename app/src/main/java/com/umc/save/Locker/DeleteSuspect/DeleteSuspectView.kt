package com.umc.save.Locker.DeleteSuspect

interface DeleteSuspectView {
    fun onDeleteSuspectSuccess(code : Int, result : String)
    fun onDeleteSuspectFailure(code : Int, message : String)

}