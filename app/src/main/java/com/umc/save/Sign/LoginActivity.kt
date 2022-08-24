package com.umc.save.Sign

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import com.umc.save.Home.option.AlarmRVAdapter
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.*
import com.umc.save.databinding.ActivityLoginBinding
import java.text.SimpleDateFormat
import java.util.*


class LoginActivity : AppCompatActivity(), LoginView, AutoLoginView {


    lateinit var binding: ActivityLoginBinding
    private var mIsShowPass = false
    //토큰 가져오기
    var token = App.prefs.token

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //자동로그인
        //토큰 가져오기
        val token = App.prefs.token
        //토큰이 존재한다면 자동로그인을 해라
        if (token != null) {
            autologin()
        }

        //login btn is no empty -> change color
        val EmailEditText = findViewById<EditText>(R.id.login_id_et)
        val PwEditText = findViewById<EditText>(R.id.login_password_et)

        EmailEditText.addTextChangedListener (object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (PwEditText.text.toString().isNotEmpty() && binding.loginIdEt.text.toString().isNotEmpty())
                    binding.loginSignInBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
                else
                    binding.loginSignInBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_gray))
            }
            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) { }
        })

        PwEditText.addTextChangedListener (object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (PwEditText.text.toString().isNotEmpty() && binding.loginIdEt.text.toString().isNotEmpty())
                    binding.loginSignInBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
                else
                    binding.loginSignInBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_gray))
            }
            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) { }
        })

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


    //로그인
    private fun login() {
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
   private fun saveJwt2(jwt: String){
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }


    //자동 로그인
    private fun autologin() {
        val autoLoginService = AutoLoginService()
        autoLoginService.setAutoLoginView(this)
        token?.let { autoLoginService.getAutologin(it) }
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

                userIdx_var.UserIdx.UserIdx = result.userIdx
                App.prefs.token = result.jwt
                startMainActivity()
            }
        }
    }

    //로그인 실패
    override fun onLoginFailure(code: Int, message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

    //자동 로그인
    override fun onAutoLoginSuccess(code: Int, result: aResult) {

        //자동 로그인 코드
        Log.d("AUTO-LOGIN-USER-SUCCESS",result.toString())
        userIdx_var.UserIdx.UserIdx = result.userIdx
        startMainActivity()

    }

    override fun userAutoNotExist(code: Int, message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        Log.d("AUTO-LOGIN-USER-FAIL",message)
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
        finish()
    }
}
