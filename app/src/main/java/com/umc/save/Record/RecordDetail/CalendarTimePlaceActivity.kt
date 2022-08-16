package com.umc.save.Record.RecordDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.get
import com.prolificinteractive.materialcalendarview.*
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
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
    var selectedDate : CalendarDay = CalendarDay.today()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTimePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        val sdf= SimpleDateFormat("yyyy년 MM월 dd일")
        var data_date : String = ""


//        binding.calendar.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
//            override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, day: Int) {
//
//                var month2 = month + 1
//                if(month2 == 13)
//                    month2 = 1
//                data_date = year.toString() + "/" + month2.toString() + "/" + day.toString()
//                abuse_var.abuse.a_date = year.toString() + "년 " + month2.toString() + "월 " + day.toString() + "일"
//            }
//        })

        val weekdayList = arrayOf("SUN","MON","TUE","WED","THU","FRI","SAT")
        val monthList = arrayOf("01","02","03","04","05","06","07","08","09","10","11","12")
        val disabledDates = hashSetOf<CalendarDay>()
        disabledDates.add( CalendarDay.from(2020,1,1)
        )

        binding.calendar.apply {
            setWeekDayLabels(weekdayList)
            setTitleMonths(monthList)
//            setTitleFormatter()
            setTitleFormatter(MyTitleFormatter())

        }


        binding.calendar.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                if(selected)
                    data_date = sdf.format(date.date)
                Log.d("현재 시간 ==================================== ", data_date)
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

        binding.recordPlace.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if(data_date != null && abuse_var.abuse.a_time != null && abuse_var.abuse.a_date != null
                    && !binding.recordPlace.text.equals(""))
                    binding.btnNext.setBackgroundResource(R.drawable.fragment_dark_red_background)

                else{
                    binding.btnNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
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


    inner class MyTitleFormatter : TitleFormatter {
        @SuppressLint("SimpleDateFormat")
        override fun format(day: CalendarDay?): CharSequence {
            val simpleDateFormat =
                java.text.SimpleDateFormat("yyyy / MM")

            return simpleDateFormat.format(Calendar.getInstance().time)
        }

    }

    inner class DayDisableDecorator : DayViewDecorator {
        private var dates = HashSet<CalendarDay>()
        private var today: CalendarDay

        constructor(dates: HashSet<CalendarDay>, today: CalendarDay) {
            this.dates = dates
            this.today = today
        }

        override fun shouldDecorate(day: CalendarDay): Boolean {
            // 휴무일 || 이전 날짜
            return dates.contains(day) || day.isBefore(today)
        }

        override fun decorate(view: DayViewFacade?) {
            view?.let { it.setDaysDisabled(true) }
        }
    }




}