package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.R
import com.umc.save.databinding.ActivityOffenderRecordBinding

class OffenderRecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityOffenderRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffenderRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordAdd.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

        binding.recordNext.setOnClickListener{
            startActivity(Intent(this, RecordDoneActivity::class.java))
        }
    }
}