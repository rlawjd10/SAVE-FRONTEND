package com.umc.save.Sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.umc.save.R
import com.umc.save.Sign.User.User
import com.umc.save.Sign.User.UserService
import com.umc.save.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initActionBar()
    }

    /*private fun getUser() :User {

        return()
    }*/

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

    override fun onSignUpSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSignUpFailure() {
        TODO("Not yet implemented")
    }

   /* private fun signUp() {

       val userService = UserService()
        userService.setSignUpView(this)

        userService.signUp(getUser())
    }*/
}