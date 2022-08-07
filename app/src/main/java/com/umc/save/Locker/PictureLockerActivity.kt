package com.umc.save.Locker

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
       val image = intent.getIntExtra("picture",0)
        //url로 바꿔야 한다!
        binding.pictureZoomInIv.setImageResource(image)
//        Glide.with(this)
//                .load(R.drawable.img_file_name)
//                .into(itemView.imageView)
    }


}

