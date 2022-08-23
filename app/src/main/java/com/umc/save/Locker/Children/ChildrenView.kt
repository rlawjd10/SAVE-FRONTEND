package com.umc.save.Locker.Children

import com.umc.save.Locker.data.Child

interface ChildrenView {
    fun onGetChildSuccess(code : Int, result : ArrayList<Child>)
    fun childNotExist(code : Int, message : String)
    fun onGetChildFailure(code : Int, message : String)
}