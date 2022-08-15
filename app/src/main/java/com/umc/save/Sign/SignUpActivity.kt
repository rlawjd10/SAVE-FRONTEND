package com.umc.save.Sign

import android.content.Intent
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
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.umc.save.R
import com.umc.save.Sign.Auth.AuthService
import com.umc.save.Sign.User.SignUpView
import com.umc.save.Sign.User.User
import com.umc.save.Sign.User.UserService
import com.umc.save.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignUpBinding
    lateinit var editText: EditText
    lateinit var _editText: EditText
    lateinit var pwlength: TextView

    private var mSignShowPass = false
    private var mSignShow2Pass = false

    private var agreeChecked = false
    private var agreeChecked2 = false
    private var agreeChecked3 = false
    private var agreeChecked4 = false

    private var nameCheck = false
    private var emailCheck = false
    private var PWCheck = false
    private var PWConfirmCheck = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = findViewById<EditText>(R.id.singup_password_input_et)
        _editText = findViewById<EditText>(R.id.singup_password_confirm_et)
        pwlength = findViewById<TextView>(R.id.signup_pw_vali_length_tv)

        val asso = findViewById<TextView>(R.id.signup_pw_vali_asso_tv)
        val allCheck = findViewById<ImageButton>(R.id.signup_consent_all_check)

        //password show -> hide
        binding.signupPasswordShowIv.setOnClickListener {
            mSignShowPass = !mSignShowPass
            showPassword(mSignShowPass, binding.signupPasswordShowIv, editText)
        }
        //password confirm show -> hide
        binding.signupPasswordConfirmShowIv.setOnClickListener {
            mSignShow2Pass = !mSignShow2Pass
            showPassword(mSignShow2Pass, binding.signupPasswordConfirmShowIv, binding.singupPasswordConfirmEt)
        }
        //password cross (x) -> erase
        binding.signupPasswordEraseIv.setOnClickListener {
            editText.text.clear()
        }
        //password confirm cross (x) -> erase
        binding.signupPasswordConfirmEraseIv.setOnClickListener {
            binding.singupPasswordConfirmEt.text.clear()
        }

        //password validation
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
                if (binding.signupPwValiLengthTv.isInvisible && binding.signupPwValiCombinationTv.isInvisible)
                    PWCheck = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.
                pwlength.visibility = View.VISIBLE
                binding.signupPwValiCombinationTv.visibility = View.VISIBLE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
                if (isPasswordLengthFormat(editText.text.toString()))
                    pwlength.visibility = View.INVISIBLE
                // 길이 유효성 검사가 보이지 않는다면 조합 유효성 검사가 위로 올라가도록
                if (binding.signupPwValiLengthTv.isInvisible) {
                    val params = binding.signupPwValiCombinationTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-12), 0, 0)
                    binding.signupPwValiCombinationTv.layoutParams = params
                    binding.signupPwValiCombinationTv.visibility = View.VISIBLE
                } else {
                    //if문만 하면 뒤로 지울 때 글씨가 겹치는 문제가 생기기 때문에 다시 원상태가 되도록
                    val params = binding.signupPwValiCombinationTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    binding.signupPwValiCombinationTv.layoutParams = params
                    binding.signupPwValiCombinationTv.visibility = View.VISIBLE
                }
                if (isPasswordCombiformat(editText.text.toString()))
                    binding.signupPwValiCombinationTv.visibility = View.INVISIBLE

            }
        })

        //password confirm coincide check
        _editText.addTextChangedListener(object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                _editText.visibility = View.VISIBLE
            }
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //비밀번호가 일치하는지 확인
                if(editText.getText().toString().equals(_editText.getText().toString())){
                    asso.visibility = View.INVISIBLE
                }
                else
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
                // 다른 유효성 검사와 겹치는 걸 방지
                if (binding.signupPwValiLengthTv.isVisible && binding.signupPwValiCombinationTv.isVisible) {
                    //앞 2개의 유효성 검사가 아직도 존재한다면 밑으로 내려가도록
                    val params = asso.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(26), 0, 0)
                    asso.layoutParams = params
                    asso.visibility = View.VISIBLE
                }
                else if(binding.signupPwValiLengthTv.isInvisible && binding.signupPwValiCombinationTv.isVisible) {
                    val params = asso.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-10), 0, 0)
                    asso.layoutParams = params
                    asso.visibility = View.VISIBLE
                }
                else if (binding.signupPwValiLengthTv.isInvisible && binding.signupPwValiCombinationTv.isInvisible){
                    //앞 2개의 유효성 검사가 보이지 않을때 제자리로
                    val params = asso.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    asso.layoutParams = params
                    asso.visibility = View.VISIBLE
                }
            }

            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) {
                if(editText.getText().toString().equals(_editText.getText().toString())){
                    asso.visibility = View.INVISIBLE
                }
                else
                    asso.visibility = View.VISIBLE

                if (asso.isInvisible) {
                    PWConfirmCheck = true
                }

            }
        })

        val nameEditText = findViewById<EditText>(R.id.singup_name_input_et)
        val emailEditText = findViewById<EditText>(R.id.singup_email_input_et)

        //checkname
        nameEditText.addTextChangedListener(object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (checkName(nameEditText.text.toString())) {
                    nameCheck = true
                }
            }

            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) { }
        })

        //checkemail
        emailEditText.addTextChangedListener(object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) {
                if (checkEmailAddress(emailEditText.text.toString())) {
                    emailCheck = true
                }
            }
        })

        //밑줄
        underlineSee(binding.signupInfoSeeTv)
        underlineSee(binding.signupInfoSecretTv)
        underlineSee(binding.signupInfoFactTv)

        //전체동의, 이용약관
        allCheck.setOnClickListener {
            agreeChecked = !agreeChecked
            onChangedClick(agreeChecked, allCheck)
            onCheckClick(agreeChecked, allCheck, binding.signupConsentAllTv)
        }
        //개인정보
        binding.signupConsentInfoCheck.setOnClickListener {
            agreeChecked2 = !agreeChecked2
            onCheckClick(agreeChecked2, binding.signupConsentInfoCheck, binding.signupConsentInfoTv)
            if (agreeChecked2 && agreeChecked3 && agreeChecked4) {
                onCheckClick(true, allCheck, binding.signupConsentAllTv)
                binding.signupCompleteBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            }
            if (!agreeChecked2 || !agreeChecked3 || !agreeChecked4) {
                onCheckClick(false, allCheck, binding.signupConsentAllTv)
            }
        }
        //비밀유지
        binding.signupConsentSecretCheck.setOnClickListener {
            agreeChecked3 = !agreeChecked3
            onCheckClick(agreeChecked3, binding.signupConsentSecretCheck, binding.signupConsentSecretTv)
            if (agreeChecked2 && agreeChecked3 && agreeChecked4) {
                onCheckClick(true, allCheck, binding.signupConsentAllTv)
                binding.signupCompleteBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            }
            if (!agreeChecked2 || !agreeChecked3 || !agreeChecked4) {
                onCheckClick(false, allCheck, binding.signupConsentAllTv)
            }
        }
        //허위사실
        binding.signupConsentFactCheck.setOnClickListener {
            agreeChecked4 = !agreeChecked4
            onCheckClick(agreeChecked4, binding.signupConsentFactCheck, binding.signupConsentFactTv)
            if (agreeChecked2 && agreeChecked3 && agreeChecked4) {
                onCheckClick(true, allCheck, binding.signupConsentAllTv)
                binding.signupCompleteBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            }
            if (!agreeChecked2 || !agreeChecked3 || !agreeChecked4) {
                onCheckClick(false, allCheck, binding.signupConsentAllTv)
            }
        }

        //sign up
        binding.signupCompleteBtn.setOnClickListener {
            signUp()
        }

        initActionBar()

    }

    private fun getUser(): User {
        val isSnsauth: Int = 0
        val name: String = binding.singupNameInputEt.text.toString()
        val phone: String = binding.signupPhoneInputEt.text.toString()
        val email: String = binding.singupEmailInputEt.text.toString()
        val pwd: String = binding.singupPasswordInputEt.text.toString()

        return User(isSnsauth, name, phone, email, pwd)
    }

    //sign up
    private fun signUp() {
        val userService = UserService()
        userService.setSignUpView(this)

        userService.signUp(getUser())

        Log.d("SIGNUP-ACT/ASYNC", "Hello, SAVE")
    }

    //밑줄
    private fun underlineSee(view: TextView) {
        val under = SpannableString("보기")
        under.setSpan(UnderlineSpan(), 0, under.length, 0)
        view.text = under
    }

    //dp값으로 변경_비밀번호 유효성 검사에서 margin값을 변경하기 위함
    private fun changeDP(value: Int) : Int {
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

    //app bar
    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text = "회원가입"
        appBartext.visibility = View.VISIBLE
        appBarComplete.text = "완료"
        appBarComplete.visibility = View.INVISIBLE
        appBarBtn.setOnClickListener { onBackPressed() }
    }

    //비밀번호 show -> hide
    private fun showPassword(isShow: Boolean, show: ImageView, text: EditText) {

        if(isShow) {
            text.transformationMethod = HideReturnsTransformationMethod.getInstance()
            show.setImageResource(R.drawable.icn_hide_normal)
        } else {
            text.transformationMethod = PasswordTransformationMethod.getInstance()
            show.setImageResource(R.drawable.icn_show_normal)
        }
        //cursor 이동
        text.setSelection(text.text.toString().length)
    }

    //===password validation===
    //8~20자 이내로 입력하여 주십시오.
    fun isPasswordLengthFormat(password: String): Boolean {
        return password.matches("^([^\\s]){8,20}\$".toRegex())
    }

    //영문, 숫자를 조합하여 입력하여 주십시오.
    fun isPasswordCombiformat(password: String): Boolean {
        return password.matches("^(?=.*\\d)(?=.*[a-z])([^\\s]).{0,}\$".toRegex())
    }

    //이름 check -> 2글자 이상만 쓰면 OK
    fun checkName(text: String) : Boolean {
        return text.matches("^([^\\s]){2,}\$".toRegex())
    }

    //이메일 check
    fun checkEmailAddress(text: String) : Boolean {
        //return text.matches("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}\$".toRegex())
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    //===Terms of Service===
    //전체동의, 이용약관
    private fun onCheckClick(isAgree: Boolean, show: ImageButton, text: TextView) {

        if (isAgree) {
            text.setTextColor(ContextCompat.getColor(applicationContext, R.color.dark_red))
            show.setImageResource(R.drawable.icn_check_02_on)
        } else {
            text.setTextColor(ContextCompat.getColor(applicationContext, R.color.dark_gray))
            show.setImageResource(R.drawable.icn_check_02_off)
            binding.signupCompleteBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_gray))
        }
    }

    //전체동의를 누르면 다 눌리도록_전체동의만 할 수 있도록.
    //따로 함수를 만든 이유 : 나머지 3개를 다 눌렀을 때도 전체동의가 true가 되게 하기 위해서
    private fun onChangedClick(isAgree: Boolean, show: ImageButton) {

        val cBox1 = findViewById<ImageButton>(R.id.signup_consent_info_check)
        val cBox2 = findViewById<ImageButton>(R.id.signup_consent_secret_check)
        val cBox3 = findViewById<ImageButton>(R.id.signup_consent_fact_check)

        when (show.id) {
            R.id.signup_consent_all_check -> {
                onCheckClick(isAgree, cBox1, binding.signupConsentInfoTv)
                onCheckClick(isAgree, cBox2, binding.signupConsentSecretTv)
                onCheckClick(isAgree, cBox3, binding.signupConsentFactTv)
            }
        }
        binding.signupCompleteBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.dark_red))
    }


    //회원가입 성공 시 activity 이동
    override fun onSignUpSuccess() {
        startActivity(Intent(this, SignUpCompleteActivity::class.java))
    }

    override fun onSignUpFailure() {
        TODO("Not yet implemented")
    }



}

