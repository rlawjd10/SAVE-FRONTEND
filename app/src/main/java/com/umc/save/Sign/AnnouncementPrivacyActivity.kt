package com.umc.save.Sign

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.save.databinding.ActivityPrivacyAnnouncementBinding

class AnnouncementPrivacyActivity : AppCompatActivity() {

    lateinit var binding : ActivityPrivacyAnnouncementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyAnnouncementBinding.inflate(layoutInflater)

        binding.closeTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.closeTv.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

}

