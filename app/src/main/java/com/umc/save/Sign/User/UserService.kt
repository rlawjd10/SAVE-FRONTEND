package com.umc.save.Sign.User

import com.umc.save.Sign.LoginView

//로그인 _ view, api 관리
class UserService {
    private lateinit var loginView: LoginView

    fun setLohinView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun login(user: User) {
        //val loginService = getRetrofit().create(UserRetrofitInterface::class.java)
    }
}