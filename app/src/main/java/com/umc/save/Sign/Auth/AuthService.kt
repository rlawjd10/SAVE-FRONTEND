package com.umc.save.Sign.Auth

import android.content.ContentValues.TAG
import android.util.Log
import com.umc.save.Sign.LoginView
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//로그인
//view, api를 관리하는 클래스.

class AuthService {
    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun login(auth: Auth) {
        val loginService = getRetrofit().create(AuthRetrofitInterface::class.java)

        //비동기 방식으로 통신을 요청
        //통신 종료 후 이벤트 처리를 위해 Callback 등록
        loginService.login(auth).enqueue(object : Callback<AuthResponse> {
            //통신에 성공한 경우
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                //정상적으로 통신이 성공된 경우
                if (response.isSuccessful()) {
                    Log.d("LOGIN/SUCCESS", response.toString())
                    val loginResponse: AuthResponse = response.body()!!

                    when (val code = loginResponse.code) {
                        1000 -> loginView.onLoginSuccess(code,loginResponse.result!!)
                        else -> loginView.onLoginFailure()
                    }
                }
                //통신이 실패 (응답 코드)
                else {

                }

            }

            //통신에 실패한 경우 (시스템적인 이유)
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }
        })
    }
}