package com.umc.save.Sign

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
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.*
import com.umc.save.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {

    companion object {
        //만들어져있는 SharedPreferences 사용 //재생성x
        lateinit var sSharedPreferences: SharedPreferences

        // JWT Token 헤더 키 값
        var X_ACCESS_TOKEN = "X_ACCESS_TOKEN"
    }

    lateinit var binding: ActivityLoginBinding
    private var mIsShowPass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sSharedPreferences = applicationContext.getSharedPreferences("app", Context.MODE_PRIVATE)

        //social login to MainActivity
        binding.loginKakaoTv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
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
                val jwtResponse: String = result.jwt
                Log.d("x-access-token", result.jwt)
                //jwt 토큰 보냄
                sSharedPreferences.edit().putString(X_ACCESS_TOKEN, jwtResponse).apply()
                Log.d("x-access-token", X_ACCESS_TOKEN)
                // saveJwt2(result.jwt)
                //result.jwt?.let { saveJwt2(it) }
                userIdx_var.UserIdx.UserIdx = result.userIdx
                startMainActivity()
            }
        }
    }

    //로그인 실패
    override fun onLoginFailure() {

    }

    override fun onAutoLoginSuccess(code: Int, result: Result) {
        startMainActivity()
    }

}