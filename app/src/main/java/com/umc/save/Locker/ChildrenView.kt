package com.umc.save.Locker

interface ChildrenView {
    fun onGetChildSuccess(code : Int, result : ArrayList<Child>)
    fun childNotExist(code : Int, message : String)
    fun onGetChildFailure(code : Int, message : String)
}