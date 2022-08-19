package com.umc.save.Sign.User

interface SignUpView {
    fun onSignUpSuccess()
    fun onSignUpFailure(code: Int, message : String)
}