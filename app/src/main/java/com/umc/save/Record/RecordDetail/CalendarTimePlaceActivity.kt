package com.umc.save.Record.RecordDetail

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.view.get
import com.umc.save.R
import com.umc.save.Record.Auth.AbuseSituation.abuse_var
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityCalendarTimePlaceBinding
import java.util.*

class CalendarTimePlaceActivity : AppCompatActivity(), TimePicker.OnTimeChangedListener {
    lateinit var binding: ActivityCalendarTimePlaceBinding
    private var calendar = Calendar.getInstance()
    private lateinit var time : TimePicker.OnTimeChangedListener
    var hour1 : Int = 0
    var minute1 : Int = 0
    var am_pm : String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTimePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()


        var year : String = calendar.get(Calendar.YEAR).toString()

        abuse_var.abuse.a_date = SimpleDateFormat("yyyy년 MM월 dd일").toString()




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

    override fun onTimeChanged(p0: TimePicker?, hour2: Int, minute2: Int) {
        hour1 = hour2
        minute1 = minute2

        if(hour2 > 12) {
            hour1 = hour2 - 12
            am_pm = "PM"
        } else {
            am_pm = "AM"
        }

        abuse_var.abuse.a_time = am_pm + " " + hour1 + ":" + minute1
    }
}