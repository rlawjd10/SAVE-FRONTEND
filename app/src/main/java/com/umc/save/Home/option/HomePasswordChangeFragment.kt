package com.umc.save.Home.option

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomePasswordChangeBinding

class HomePasswordChangeFragment : Fragment() {

    private var mSignShowPass = false
    private var mSignShow2Pass = false
    private var mSignShow3Pass = false

    private lateinit var binding: FragmentHomePasswordChangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomePasswordChangeBinding.inflate(layoutInflater)

        //x 누르면 다 지워지도록
        binding.deletePasswordIv.setOnClickListener {
            binding.originPasswordEt.text.clear()
        }
        binding.deletePassword2Iv.setOnClickListener {
            binding.newPasswordEt.text.clear()
        }
        binding.deletePassword3Iv.setOnClickListener {
            binding.notifyPasswordEt.text.clear()
        }

        //눈 show<->hide
        binding.showPasswordIv.setOnClickListener {
            mSignShowPass = !mSignShowPass
            showPassword(mSignShowPass, binding.showPasswordIv, binding.originPasswordEt)
        }
        binding.showPassword2Iv.setOnClickListener {
            mSignShow2Pass = !mSignShow2Pass
            showPassword(mSignShow2Pass, binding.showPassword2Iv, binding.newPasswordEt)
        }
        binding.showPassword3Iv.setOnClickListener {
            mSignShow3Pass = !mSignShow3Pass
            showPassword(mSignShow3Pass, binding.showPassword3Iv, binding.notifyPasswordEt)
        }

        //비밀번호 유효성 검사
        binding.newPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.
                binding.homePasswordChangeLengthTv.visibility = View.VISIBLE
                binding.homePasswordChangeCombiTv.visibility = View.VISIBLE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
                //길이가 8-20이면 유효성 검사하 보이지 않게 된다.
                if (isPasswordLengthFormat(binding.newPasswordEt.text.toString()))
                    binding.homePasswordChangeLengthTv.visibility = View.INVISIBLE
                // 길이 유효성 검사가 보이지 않는다면 조합 유효성 검사가 위로 올라가도록
                if (binding.homePasswordChangeLengthTv.isInvisible) {
                    val params = binding.homePasswordChangeCombiTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-12), 0, 0)
                    binding.homePasswordChangeCombiTv.layoutParams = params
                    binding.homePasswordChangeCombiTv.visibility = View.VISIBLE
                } else {
                    //if문만 하면 뒤로 지울 때 글씨가 겹치는 문제가 생기기 때문에 다시 원상태가 되도록
                    val params = binding.homePasswordChangeCombiTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    binding.homePasswordChangeCombiTv.layoutParams = params
                    binding.homePasswordChangeCombiTv.visibility = View.VISIBLE
                }
                if (isPasswordCombiformat(binding.newPasswordEt.text.toString()))
                    binding.homePasswordChangeCombiTv.visibility = View.INVISIBLE
            }
        })
        //비밀번호 일치성 검사
        binding.notifyPasswordEt.addTextChangedListener(object : TextWatcher {
            // EditText에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
            }
            // EditText에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //비밀번호가 일치하는지 확인
                if(binding.newPasswordEt.getText().toString().equals(binding.notifyPasswordEt.getText().toString())){
                    binding.homePasswordChangeAssoTv.visibility = View.INVISIBLE
                }
                else
                    binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
                // 다른 유효성 검사와 겹치는 걸 방지
                if (binding.homePasswordChangeLengthTv.isVisible && binding.homePasswordChangeCombiTv.isVisible) {
                    //앞 2개의 유효성 검사가 아직도 존재한다면 밑으로 내려가도록
                    val params = binding.homePasswordChangeAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    binding.homePasswordChangeAssoTv.layoutParams = params
                    binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
                }
                else if(binding.homePasswordChangeLengthTv.isInvisible && binding.homePasswordChangeCombiTv.isVisible) {
                    val params = binding.homePasswordChangeAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-10), 0, 0)
                    binding.homePasswordChangeAssoTv.layoutParams = params
                    binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
                }
                else if (binding.homePasswordChangeLengthTv.isInvisible && binding.homePasswordChangeCombiTv.isInvisible){
                    //앞 2개의 유효성 검사가 보이지 않을때 제자리로
                    val params = binding.homePasswordChangeAssoTv.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, changeDP(-13), 0, 0)
                    binding.homePasswordChangeAssoTv.layoutParams = params
                    binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
                }
            }

            // EditText 입력이 끝난 후
            override fun afterTextChanged(p0: Editable?) {
                if(binding.newPasswordEt.getText().toString().equals(binding.notifyPasswordEt.getText().toString())){
                    binding.homePasswordChangeAssoTv.visibility = View.INVISIBLE
                }
                else
                    binding.homePasswordChangeAssoTv.visibility = View.VISIBLE
            }
        })

        //앱바 호출
        initActionBar()
        return binding.root
    }
    //============

    //앱바
    private fun initActionBar() {
        binding.mainActionbar.appbarPageNameTv.text = "비밀번호 변경"
        binding.mainActionbar.appbarCompleteTv.visibility = View.VISIBLE

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }
    }

    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .addToBackStack(null)
            .commit()
    }

    //비밀번호 눈 show <-> hide
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
        /*return password.matches("^(?=.*\\d)(?=.*[a-z])([^\\s]).{0,}\$".toRegex())*/
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{0,}.\$".toRegex())
    }

    //dp값으로 변경_비밀번호 유효성 검사에서 margin값을 변경하기 위함
    private fun changeDP(value: Int) : Int {
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

}