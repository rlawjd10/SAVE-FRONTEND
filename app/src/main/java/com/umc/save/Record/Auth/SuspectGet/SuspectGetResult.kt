package com.umc.save.Record.Auth.SuspectGet

import java.util.ArrayList

interface SuspectGetResult {
    fun getChildSuccess(code: Int, result: ArrayList<Suspect>)
    fun getChildFailure(code : Int, message : String)
}