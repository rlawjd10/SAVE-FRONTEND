package com.umc.save.Sign

import android.content.Intent
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.save.Home.HomeFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.Auth
import com.umc.save.Sign.Auth.AuthService
import com.umc.save.Sign.Auth.Result
import com.umc.save.databinding.ActivityLoginBinding
import java.nio.channels.InterruptedByTimeoutException

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //social login
        binding.loginKakaoBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //로그인
        binding.loginSignInBtn.setOnClickListener {
            login()

        }
        //회원가입 클릭 -> 회원가입 Activity로 이동하도록
        binding.signUpEmailTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun login() {
        //이메일 또는 비밀번호를 입력하지 않았을 때 토스트 띄우기
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(getUser())
    }

    //값 받기
    private fun getUser(): Auth {
        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        return Auth(email = email, password = password)
    }

    //로그인 성공 시 받은 userIdx를 저장하는 함수 -> 사용자를 식별하기 위함함
    private fun saveJwt(jwt: Int) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putInt("jwt", jwt)
        editor.apply()
    }

    //로그인 성공 시 받은 jwt를 저장하는 함수 -> 사용자를 식별하기 위함함
   private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }


    //activity 이동
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //로그인 성공
    override fun onLoginSuccess(code: Int, result: Result) {
        when(code) {
            1000 -> {
                saveJwt2(result.jwt)
                startMainActivity()
            }
        }
    }

    //로그인 실패
    override fun onLoginFailure() {
        TODO("Not yet implemented")
    }

}