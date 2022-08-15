package com.umc.save.Sign.Auth


//로그인 실패&성공 함수 관리를 위한 인터페이스
interface LoginView {
    fun onLoginSuccess(code : Int, result : Result)
    fun onLoginFailure(code: Int, message : String)
}