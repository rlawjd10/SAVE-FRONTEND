package com.umc.save.Locker.Suspect

import com.umc.save.Locker.data.Suspect

interface SuspectsView {
    fun onGetSuspectsSuccess(code: Int, result: ArrayList<Suspect>)
    fun suspectNotExist(code : Int, message : String)
    fun onGetSuspectsFailure(code : Int, message : String)
}