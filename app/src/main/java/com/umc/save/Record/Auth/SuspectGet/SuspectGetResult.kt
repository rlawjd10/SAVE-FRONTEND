package com.umc.save.Record.Auth.SuspectGet

import java.util.ArrayList

interface SuspectGetResult {
    fun getSuspectSuccess(code: Int, result: ArrayList<Suspect>)
    fun getSuspectFailure(code : Int, message : String)
}