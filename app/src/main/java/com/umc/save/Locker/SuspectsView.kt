package com.umc.save.Locker

import com.umc.save.Record.Suspect

interface SuspectsView {
    fun onGetSuspectsSuccess(code: Int, result: ArrayList<Suspect>)
    fun suspectNotExist(code : Int, message : String)
    fun onGetSuspectsFailure(code : Int, message : String)
}