package com.umc.save.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
    private var mIsShowPass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //social login to MainActivity
        binding.loginKakaoBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //로그인 클릭 -> login 가능한지 method 이동
        binding.loginSignInBtn.setOnClickListener {
            login()

        }
        //회원가입 클릭 -> 회원가입 Activity로 이동하도록
        binding.signUpEmailTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //이메일로 회원가입하는 TextView underline 추가
        val text = SpannableString("이메일로 회원가입")
        text.setSpan(UnderlineSpan(), 0, text.length, 0)
        binding.signUpEmailTv.text = text

        //비밀번호 eye icon -> show/hide
        binding.showPasswordIv.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
    }

    //비밀번호 eye icon -> show/hide
    private fun showPassword(isShow: Boolean) {
        val show = binding.showPasswordIv
        val text = binding.loginPasswordEt

        if (isShow) {
            text.transformationMethod = HideReturnsTransformationMethod.getInstance()
            show.setImageResource(R.drawable.icn_hide_normal)
        }
        else {
            text.transformationMethod = PasswordTransformationMethod.getInstance()
            show.setImageResource(R.drawable.icn_show_normal)
        }
        //cursor가 맨 뒤로 가도록
        text.setSelection(text.text.toString().length)
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

        authService.login(getAuth())
    }

    //로그인 값 받기
    private fun getAuth(): Auth {
        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        return Auth(email = email, password = password)
    }

    //로그인 성공 시 받은 userIdx를 저장하는 함수 -> 사용자를 식별하기 위함
    private fun saveJwt(jwt: Int) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        //data를 저장하기 위한 editor
        val editor = spf.edit()

        //저장할 값들 -> (key, value)
        editor.putInt("jwt", jwt)
        //모든 저장이 끝났을 때 editor를 완료하기 위함.
        editor.apply()
    }

    //로그인 성공 시 받은 jwt를 저장하는 함수 -> 사용자를 식별하기 위함
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
                //로그인 성공할 시 유저 인덱스와 유저jwt를 저장하고 화면 이동
                saveJwt(result.userIdx)
                saveJwt2(result.jwt)
                startMainActivity()
            }
        }
    }

    //로그인 실패
    override fun onLoginFailure(code: Int, message : String) {
        /*when(code) {
            2003 -> Toast.makeText(LoginActivity(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            2004 -> Toast.makeText(LoginActivity(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            2101 -> Toast.makeText(LoginActivity(), "비밀번호 형식을 확인해주세요", Toast.LENGTH_SHORT).show()
            2103 -> Toast.makeText(LoginActivity(), "이메일 형식을 확인해주세요", Toast.LENGTH_SHORT).show()
            2334 -> Toast.makeText(LoginActivity(), "해당 이메일은 존재하지 않습니다", Toast.LENGTH_SHORT).show()
            3010 -> Toast.makeText(LoginActivity(), "로그인이 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }*/
    }

}