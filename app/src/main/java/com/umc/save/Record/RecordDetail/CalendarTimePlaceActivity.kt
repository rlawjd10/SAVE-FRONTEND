package com.umc.save.Record.RecordDetail

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.get
import com.umc.save.R
import com.umc.save.Record.Auth.AbuseSituation.AbuseSituation
import com.umc.save.Record.Auth.AbuseSituation.abuse_var
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Child
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityCalendarTimePlaceBinding
import java.util.*

class CalendarTimePlaceActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarTimePlaceBinding


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTimePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        val sdf= SimpleDateFormat("yyyy년 MM월 dd일")
        var data_date = sdf.format(binding.calendar.date)


        binding.calendar.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, day: Int) {

                var month2 = month + 1
                if(month2 == 13)
                    month2 = 1
                data_date = year.toString() + "/" + month2.toString() + "/" + day.toString()
                abuse_var.abuse.a_date = year.toString() + "년 " + month2.toString() + "월 " + day.toString() + "일"
            }
        })

        binding.timePicker.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, hour: Int, minute: Int) {

                var am_pm : String = ""
                var hour2 : Int = 0

                if(hour > 12) {
                    hour2 = hour - 12
                    am_pm = "PM"
                } else {
                    hour2 = hour
                    am_pm = "AM"
                }

                abuse_var.abuse.a_time = am_pm + " " + hour2 + ":" + minute
            }
        })

        binding.btnNext.setOnClickListener{
            Log.d("시간(sdf) ==================================== ", data_date)
            Log.d("시간 ==================================== ", abuse_var.abuse.a_time)
            Log.d("날짜 ==================================== ", abuse_var.abuse.a_date)
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