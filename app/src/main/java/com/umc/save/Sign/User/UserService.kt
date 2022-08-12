package com.umc.save.Sign.User


import com.umc.save.Sign.AuthResponse
import com.umc.save.Sign.LoginView
import com.umc.save.Sign.SignUpView
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//회원가입
// view, api 관리
class UserService {
    private lateinit var signUpView: SignUpView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun signUp(user: User) {
        val signUpService = getRetrofit().create(UserRetrofitInterface::class.java)

        signUpService.signUp(user).enqueue(object : Callback<AuthResponse> {
            //정상적인 통신이 성공
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                TODO("Not yet implemented")
            }

            //실패처리
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
