package com.umc.save.Sign

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.R
import com.umc.save.databinding.ActivitySignUpCompleteBinding

class SignUpCompleteActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpCompleteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_up_complete)

        //complete to login Activty
        binding.gotoLoginBtn.setOnClickListener {
            startActivity(Intent(this@SignUpCompleteActivity, LoginActivity::class.java))
        }
    }
}