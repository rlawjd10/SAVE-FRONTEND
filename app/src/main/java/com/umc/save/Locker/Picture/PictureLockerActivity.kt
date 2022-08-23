package com.umc.save.Locker.Picture

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.umc.save.databinding.ActivityLockerPictureBinding
import com.umc.save.databinding.ActivityLockerPlayerBinding

class PictureLockerActivity : AppCompatActivity() {

    lateinit var binding : ActivityLockerPictureBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerPictureBinding.inflate(layoutInflater)

        initImage()

        binding.closeIv.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    private fun initImage() {
       val imageUrl = intent.getStringExtra("picture")
        //url로 바꿔야 한다!
//        binding.pictureZoomInIv.setImageResource(image)
        Glide.with(this)
                .load(imageUrl)
                .into(binding.pictureZoomInIv)
    }


}

