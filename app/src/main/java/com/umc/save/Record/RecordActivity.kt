package com.umc.save.Record

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.save.R
import com.umc.save.databinding.ActivityRecordMainBinding

class RecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_main)
        binding = ActivityRecordMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordNewChild.setOnClickListener{
            startActivity(Intent(this, ChildRecordActivity::class.java))
        }
    }
}