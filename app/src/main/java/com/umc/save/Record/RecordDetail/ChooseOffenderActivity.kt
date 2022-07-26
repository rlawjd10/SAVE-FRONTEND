package com.umc.save.Record.RecordDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.databinding.ActivityAbuseTypeBinding
import com.umc.save.databinding.ActivityChooseOffenderBinding

class ChooseOffenderActivity : AppCompatActivity() {
    lateinit var binding : ActivityChooseOffenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseOffenderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}