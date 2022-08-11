package com.umc.save.Locker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.umc.save.databinding.ActivityLockerDeleteChildBinding
import com.umc.save.databinding.ActivityLockerPictureBinding

class RecordDeleteLockerActivity : AppCompatActivity() {

    lateinit var binding : ActivityLockerDeleteChildBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockerDeleteChildBinding.inflate(layoutInflater)


        binding.deleteBtn.setOnClickListener {
//            finish()
        }

        setContentView(binding.root)
    }



}
