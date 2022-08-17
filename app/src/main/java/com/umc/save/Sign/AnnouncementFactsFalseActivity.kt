package com.umc.save.Sign

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.umc.save.databinding.ActivityFalseFactsAnnouncementBinding
import com.umc.save.databinding.ActivityLockerPictureBinding
import com.umc.save.databinding.ActivityPrivacyAnnouncementBinding

class AnnouncementFactsFalseActivity : AppCompatActivity() {

    lateinit var binding : ActivityFalseFactsAnnouncementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFalseFactsAnnouncementBinding.inflate(layoutInflater)

        binding.closeTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.closeTv.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

}

