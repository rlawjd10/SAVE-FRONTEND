package com.umc.save.Sign

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.setMargins
import androidx.core.widget.addTextChangedListener
import com.umc.save.R
import com.umc.save.Sign.User.User
import com.umc.save.Sign.User.UserService
import com.umc.save.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignUpBinding
    lateinit var editText: EditText
    lateinit var _editText: EditText
    lateinit var pwlength: TextView

    private var mSignShowPass = false
    private var mSignShow2Pass = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = findViewById<EditText>(R.id.singup_password_input_et)
        _editText = findViewById<EditText>(R.id.singup_password_confirm_et)
        pwlength = findViewById<TextView>(R.id.signup_pw_vali_length_tv)

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
                    binding.signupPwValiAssoTv.visibility = View.INVISIBLE
                }
                else
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
                // 다른 유효성 검사와 겹치는 걸 방지
                if (binding.signupPwValiLengthTv.isVisible && binding.signupPwValiCombinationTv.isVisible) {
                    //앞 2개의 유효성 검사가 아직도 존재한다면 밑으로 내려가도록
                    val params = binding.signupPwValiAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(26), 0, 0)
                    binding.signupPwValiAssoTv.layoutParams = params
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
                }
                else if(binding.signupPwValiLengthTv.isInvisible && binding.signupPwValiCombinationTv.isVisible) {
                    val params = binding.signupPwValiAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-10), 0, 0)
                    binding.signupPwValiAssoTv.layoutParams = params
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
                }
                else if (binding.signupPwValiLengthTv.isInvisible && binding.signupPwValiCombinationTv.isInvisible){
                    //앞 2개의 유효성 검사가 보이지 않을때 제자리로
                    val params = binding.signupPwValiAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    binding.signupPwValiAssoTv.layoutParams = params
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
                }
            }

            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) {
                if(editText.getText().toString().equals(_editText.getText().toString())){
                    binding.signupPwValiAssoTv.visibility = View.INVISIBLE
                }
                else
                    binding.signupPwValiAssoTv.visibility = View.VISIBLE
            }
        })

        //밑줄
        underlineSee(binding.signupInfoSeeTv)
        underlineSee(binding.signupInfoSecretTv)
        underlineSee(binding.signupInfoFactTv)

        //전체동의, 이용약관
        /*binding.signupConsentAllCheck.setOnClickListener {
            onCheckChanged(binding.signupConsentAllCheck)
        }
        binding.signupConsentInfoCheck.setOnClickListener {
            onCheckChanged(binding.signupConsentInfoCheck)
        }
        binding.signupConsentSecretCheck.setOnClickListener {
            onCheckChanged(binding.signupConsentSecretCheck)
        }
        binding.signupConsentFactCheck.setOnClickListener {
            onCheckChanged(binding.signupConsentFactCheck)
        }*/

        initActionBar()

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




    /*private fun onCheckChanged(compoundButton: CompoundButton) {
        when(compoundButton.id) {
            R.id.signup_consent_all_check -> {
                if (binding.signupConsentAllCheck.isChecked) {
                    binding.signupConsentInfoCheck.isChecked = true
                    binding.signupConsentFactCheck.isChecked = true
                    binding.signupConsentSecretCheck.isChecked = true
                } else {
                    binding.signupConsentInfoCheck.isChecked = false
                    binding.signupConsentFactCheck.isChecked = false
                    binding.signupConsentSecretCheck.isChecked = false
                }
            }
            else -> {
                binding.signupConsentAllCheck.isChecked = (
                        binding.signupConsentInfoCheck.isChecked
                                && binding.signupConsentFactCheck.isChecked
                                && binding.signupConsentSecretCheck.isChecked
                        )
            }
        }
    }*/

    override fun onSignUpSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSignUpFailure() {
        TODO("Not yet implemented")
    }



}

