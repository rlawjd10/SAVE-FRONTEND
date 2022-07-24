package com.umc.save.Record.RecordDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityCalendarTimePlaceBinding

class CalendarTimePlaceActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarTimePlaceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTimePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener{
            startActivity(Intent(this, DetailEtcActivity::class.java))
        }
    }
}