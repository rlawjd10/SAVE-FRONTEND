package com.umc.save.Record.RecordDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.R
import com.umc.save.databinding.ActivityAbuseTypeBinding
import com.umc.save.databinding.ActivityRecordMainBinding

class AbuseTypeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAbuseTypeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbuseTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}