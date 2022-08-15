package com.umc.save.Sign.User


import android.util.Log
import com.umc.save.Sign.Auth.AuthResponse
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//회원가입
// view, api 관리

class userIdx {
    object userIdx {
        var userIdx : Int = 0
    }
}

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
                if (response.isSuccessful && response.code() == 200) {
                    val signUpResponse: AuthResponse = response.body()!!

                    Log.d("SIGNUP-RESPONSE", signUpResponse.toString())

                    when (val code = signUpResponse.code) {
                        1000 -> signUpView.onSignUpSuccess()
                        else -> {
                            signUpView.onSignUpFailure()
                        }
                    }
                }
            }

            //실패처리
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
