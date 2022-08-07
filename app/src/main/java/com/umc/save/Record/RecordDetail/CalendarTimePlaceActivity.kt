package com.umc.save.Record.RecordDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.umc.save.R
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityCalendarTimePlaceBinding

class CalendarTimePlaceActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarTimePlaceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTimePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnNext.setOnClickListener{
            startActivity(Intent(this, DetailEtcActivity::class.java))
        }


    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "기록"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }
}