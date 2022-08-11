package com.umc.save.Record.Auth.ChildGet

import java.util.ArrayList


interface ChildGetResult {
    fun getChildSuccess(code: Int, result: ArrayList<Child>)
    fun getChildFailure(code : Int, message : String)
}