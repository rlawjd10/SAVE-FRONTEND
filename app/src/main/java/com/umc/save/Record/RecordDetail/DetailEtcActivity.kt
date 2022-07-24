package com.umc.save.Record.RecordDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.databinding.ActivityDetailEtcBinding

class DetailEtcActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailEtcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEtcBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}