package com.umc.save.Home.option

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.umc.save.Locker.ChildrenService
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.App
import com.umc.save.Sign.Auth.LogoutService
import com.umc.save.Sign.Auth.getLogoutView
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.Sign.LoginActivity
import com.umc.save.databinding.FragmentHomeBinding
import com.umc.save.databinding.FragmentHomeSettingsBinding


class HomeSettingsFragment : Fragment(), UserInfoView, UserOutView, getLogoutView {

    val userIdx = userIdx_var.UserIdx.UserIdx
    //토큰 가져오기
    var token = App.prefs.token

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentHomeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeSettingsBinding.inflate(layoutInflater)

        //서버에서 userInfo 가져오기
        getUserInfo()

        //앱바
        initActionBar()

        //프로필 수정 버튼 클릭
        binding.settingsProfileEditBtn.setOnClickListener {
            changeFragment(HomeEditProfileFragment())
        }

        //로그아웃 버튼 클릭
        binding.settingsLogoutBtn.setOnClickListener {
            ClickViewEvents(0)
        }

        //계정탈퇴 텍스트 클릭
        binding.settingDropOutTv.setOnClickListener {
            ClickViewEvents(1)
        }
        //게정탈퇴 이미지 클릭
        binding.settingDropOutIv.setOnClickListener{
            ClickViewEvents(1)
        }

        return binding.root
    }

    //액션바
    private fun initActionBar() {

        binding.mainActionbar.appbarPageNameTv.text = ""

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }

    }

    //User 정보 조회
    private fun getUserInfo() {
        val userInfoService = UserInfoService()
        userInfoService.setUserInfoView(this)
        userInfoService.getUserInfo(userIdx)
    }

    //탈퇴
    private fun getUserOut() {
        val userOutService = UserOutService()
        userOutService.setUserOutView(this)
        userOutService.getUserOut(userIdx)
    }

    //로그아웃
    private fun getLogout() {
        val logoutService = LogoutService()
        logoutService.setLogoutView(this)
        token?.let { logoutService.getLogoutView(it, userIdx) }
    }



    //뷰 클릭 이벤트 정의
    private fun ClickViewEvents(id: Int) {

        val dialog = HomeDialogFragment()

        when (id) {
            0 -> {
                /*val logoutbtn = arrayOf("취소", "확인")*/
                dialog.arguments = bundleOf(
                    "bodyContext" to "로그아웃 하시겠습니까?",
                    "btnOk" to "확인",
                    "btnCancel" to "취소"
                )
                dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
                    override fun onButtonNoClicked() {
                        TODO("Not yet implemented")
                    }
                    override fun onButtonOkClicked() {
                        getLogout()
                    }
                })
                dialog.show(this.childFragmentManager, "HomeDialog")
            }

            1 -> {
                /*val signutbtn = arrayOf("취소", "탈퇴")*/
                dialog.arguments = bundleOf(
                    "bodyContext" to "정말로 SAVE에서 탈퇴하시겠습니까? \n현재까지 기록된 내용들은 모두 사라집니다.",
                    "btnOk" to "탈퇴",
                    "btnCancel" to "취소"
                )
                dialog.setButtonClickListener(object: HomeDialogFragment.onButtonClickListerner {
                    override fun onButtonNoClicked() {
                        TODO("Not yet implemented")
                    }
                    override fun onButtonOkClicked() {
                        getUserOut()
                    }
                })
                dialog.show(this.childFragmentManager, "HomeDialog")
            }
        }

    }

    //로그아웃
//    private fun logout() {
//        val userIdx : Int = userIdx_var.UserIdx.UserIdx
//        val spf = activity?.getSharedPreferences("auth" , AppCompatActivity.MODE_PRIVATE)
//        val editor = spf!!.edit()
//
//        editor.remove("jwt")
//        editor.apply()
//    }

    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .addToBackStack(null)
            .commit()
    }


    //정보 조회 성공
    override fun onGetUserSuccess(code: Int, result: UserInfo) {
        Log.d("GET-USER-SUCCESS",result.toString())
        binding.userNameTv.text = result.name
        binding.userPhoneNumberTv.text = result.phone
        binding.userEmailTv.text = result.email

    }

    override fun userNotExist(code: Int, message: String) {
        Log.d("GET-USER-NOT-EXIST",message)
    }

    override fun onGetUserFailure(code: Int, message: String) {
        Log.d("GET-USER-FAILURE",message)
    }

    //탈퇴
    override fun onUserOutSuccess(code: Int, result: UserOut) {
        Log.d("OUT-USER-SUCCESS",result.toString())
        val intent = Intent(this@HomeSettingsFragment.requireContext(), LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(context, result.completeMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onUserOutFailure(code: Int, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    //로그아웃
    override fun onLogoutSuccess(code: Int, result: String) {
        Log.d("LOGOUT-SUCCESS",result)
        //토큰 지워주기
        App.prefs.token = null
        val intent = Intent(this.requireContext(), LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onLogoutFailure(code: Int, message: String) {
        Log.d("LOGOUT-Failure",message)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

