package com.umc.save.Sign.Auth

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.umc.save.Sign.AuthResponse
import com.umc.save.Sign.LoginActivity
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
        //retrofit 객체 생성
        val loginService = getRetrofit().create(AuthRetrofitInterface::class.java)

        //비동기 방식으로 통신을 요청
        //통신 종료 후 이벤트 처리를 위해 Callback 등록
        loginService.login(auth).enqueue(object : Callback<AuthResponse> {
            //통신에 성공한 경우
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                //정상적으로 통신이 성공된 경우 - API 호출 성공
                Log.d("LOGIN/RESPONSE", response.toString())
                val loginResponse: AuthResponse = response.body()!!

                when (val code = loginResponse.code) {
                    1000 -> loginView.onLoginSuccess(code,loginResponse.result)
                }
                if (response.isSuccessful()) {
                    Log.d("LOGIN/SUCCESS", response.toString())
                    val loginResponse: AuthResponse = response.body()!!

                    when (val code = loginResponse.code) {
                        1000 -> loginView.onLoginSuccess(code,loginResponse.result)
                        3010 -> loginView.onLoginFailure(code, "로그인이 실패하였습니다.")
                    }
                } else {
                    Log.d("LOGIN/FAIL", response.toString())
                    val loginResponse: AuthResponse = response.body()!!

                    when (loginResponse.code) {
                        //비밀번호를 입력해주세요
                        2003 -> {
                            Log.d("LOGIN/FAILURE_PASSWORD", response.toString())
                            loginView.onLoginFailure(2003, "비밀번호를 입력해주세요")}
                        //이메일을 입력해주세요
                        2004 -> {
                            Log.d("LOGIN/FAILURE_EMAIL", response.toString())
                            loginView.onLoginFailure(2004, "이메일을 입력해주세요")}
                        //비밀번호 형식을 확인해주세요
                        2101 -> {
                            Log.d("LOGIN/FAILURE_PASSWORD_FORM", response.toString())
                            loginView.onLoginFailure(2101, "비밀번호 형식을 확인해주세요")}
                        //이메일 형식을 확인해주세요
                        2103 -> {
                            Log.d("LOGIN/FAILURE_EMAILFORM", response.toString())
                            loginView.onLoginFailure(2103, "이메일 형식을 확인해주세요")}
                        //해당 이메일은 존재하지 않습니다
                        2334 -> {
                            Log.d("LOGIN/FAILURE_EMAILFORM", response.toString())
                            loginView.onLoginFailure(2334, "해당 이메일은 존재하지 않습니다")}
                    }
                }
            }
            //통신에 실패한 경우 (시스템적인 이유) - API 호출 실패
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE_SYSTEM", t.message.toString())
            }
        })
    }

}