package com.umc.save.Home.option

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Sign.Auth.Auth
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.databinding.FragmentHomeEditProfileBinding


class HomeEditProfileFragment : Fragment(), UserInfoView, EditUserView {

    val userIdx = userIdx_var.UserIdx.UserIdx
    lateinit var binding: FragmentHomeEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeEditProfileBinding.inflate(layoutInflater)

        //서버에서 userInfo 가져오기
        getUserInfo()

        // 비밀번호 변경 버튼 to fragment
        binding.passwordChangeBtn.setOnClickListener {
            changeFragment(HomePasswordChangeFragment())
        }


        putUserInfo()
        initActionBar()
        // Inflate the layout for this fragment
        return binding.root
    }

    //service에 보내기
    private fun putUserInfo() {
        val editUserService = EditUserService()
        editUserService.setEditUserView(this)

        editUserService.getEditUserView(EditUser(), userIdx)
    }

    //사용자에게 변경사항 받기
    private fun EditUser(): UserInfo {
        val name = binding.usernameInputEt.text.toString()
        val phone = binding.phonenumberInputEt.text.toString()
        val email = binding.emailInputEt.text.toString()

        return UserInfo(name = name, phone = phone, email = email)
    }

    //user 정보
    private fun getUserInfo() {
        val userInfoService = UserInfoService()
        userInfoService.setUserInfoView(this)
        userInfoService.getUserInfo(userIdx)
    }

    //fragment to fragment method
    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initActionBar() {
        binding.mainActionbar.appbarPageNameTv.text = "프로필 수정"
        binding.mainActionbar.appbarCompleteTv.visibility = View.VISIBLE

        binding.mainActionbar.appbarCompleteTv.setOnClickListener {
            putUserInfo()
            changeFragment(HomeSettingsFragment())
        }

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }

    }

    override fun onGetUserSuccess(code: Int, result: UserInfo) {
        Log.d("EDITPROGFILE_GET-USER-SUCCESS",result.toString())
        binding.usernameInputEt.setText(result.name)
        binding.phonenumberInputEt.setText(result.phone)
        binding.emailInputEt.setText(result.email)
    }

    override fun userNotExist(code: Int, message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetUserFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }

    override fun onEditUserSuccess(code: Int, result: mResult) {
        Log.d("EDITPROGFILE_PUT-USER-SUCCESS",result.toString())
    }

    override fun EditNotExist(code: Int, message: String) {
        TODO("Not yet implemented")
    }

    override fun onEditUserFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}