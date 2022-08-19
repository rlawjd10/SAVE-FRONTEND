package com.umc.save.Sign.Auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//로그인
//view, api를 관리하는 클래스.
class userIdx_var {
    object UserIdx {
        var UserIdx : Int = 0
    }
}

class AuthService {
    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun login(auth: Auth) {
        //retrofit 객체 생성
        val loginService = getRetrofit().create(AuthRetrofitInterface::class.java)

        //비동기 방식으로 통신을 요청
        //통신 종료 후 이벤트 처리를 위해 Callback 등록
        loginService.login(auth).enqueue(object : Callback<AuthResponse> {
            //통신에 성공한 경우
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                //정상적으로 통신이 성공된 경우 - API 호출 성공
                Log.d("LOGIN/RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val loginResponse: AuthResponse = response.body()!!
                    Log.d("LOGIN/SUCCESS", loginResponse.toString())

                    when (val code = loginResponse.code) {
                        1000 -> loginView.onLoginSuccess(code,loginResponse.result!! )
                        else -> loginView.onLoginFailure(code, loginResponse.message)
                    }
                }
            }
            //통신에 실패한 경우 (시스템적인 이유) - API 호출 실패
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE_SYSTEM", t.message.toString())
            }
        })
    }

    fun autologin(jwt : String) {
        val autologinService = getRetrofit().create(AuthRetrofitInterface::class.java)
        autologinService.autologin(jwt).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("AUTOLOGIN", response.body().toString())

                if (response.isSuccessful && response.code() == 200) {
                    val loginResponse: AuthResponse = response.body()!!
                    Log.d("LOGIN/SUCCESS", loginResponse.toString())


                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("AUTOLOGIN/FAILURE_SYSTEM", t.message.toString())
            }

        })
    }

}