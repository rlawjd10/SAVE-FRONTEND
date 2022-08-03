package com.umc.save.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.umc.save.R
import com.umc.save.Sign.Auth.Auth
import com.umc.save.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        //auth 작성하기
    }

    //값 받기
    private fun getUser(): Auth {
        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        return Auth(email = email, password = password)
    }

    //로그인 성공

    //로그인 실패

}