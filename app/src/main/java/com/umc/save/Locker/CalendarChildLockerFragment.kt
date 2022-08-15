package com.umc.save.Locker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import com.umc.save.R
import com.umc.save.databinding.FragmentLockerChildCalendarBinding
import com.umc.save.databinding.FragmentLockerChildListBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class CalendarChildLockerFragment(child: Int) : Fragment(), AbuseView {
//    , DayViewDecorator
    lateinit var binding : FragmentLockerChildCalendarBinding
    var currentPosition = 0
    private var childIdx = child
    private var recordList = ArrayList<RecordData>()
//    private final drawable : Drawable

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildCalendarBinding.inflate(inflater,container,false)

        getAbuseCases()

        val weekdayList = arrayOf("SUN","MON","TUE","WED","THU","FRI","SAT")
        val monthList = arrayOf("01","02","03","04","05","06","07","08","09","10","11","12")
        val disabledDates = hashSetOf<CalendarDay>()
        disabledDates.add( CalendarDay.from(2020,1,1)
        )
        binding.calendarView.apply {
            addDecorator(Disable(disabledDates))
            setWeekDayLabels(weekdayList)
            setTitleMonths(monthList)
//            setTitleFormatter()
            selectionMode = MaterialCalendarView.SELECTION_MODE_MULTIPLE
//            setTitleFormatter(MyTitleFormatter())

        }

        return binding.root
    }


    inner class MyTitleFormatter : TitleFormatter {
        @SuppressLint("SimpleDateFormat")
        override fun format(day: CalendarDay?): CharSequence {
            val simpleDateFormat =
                SimpleDateFormat("yyyy / MM")

            return simpleDateFormat.format(Calendar.getInstance().time)
        }

    }


//    private var dates = HashSet<CalendarDay>()
//    private var today: CalendarDay
//
//    constructor(dates: HashSet<CalendarDay>, today: CalendarDay) {
//        this.dates = dates
//        this.today = today
//    }


    inner class Disable (
        private var dates: HashSet<CalendarDay> = HashSet<CalendarDay>()

    ) : DayViewDecorator {

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return true
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setDaysDisabled(true)
        }
    }




//    override fun shouldDecorate(day: CalendarDay?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun decorate(view: DayViewFacade?) {
//        TODO("Not yet implemented")
//    }

    private fun getAbuseCases() {

        val abuseService = AbuseService()

        abuseService.setAbuseView(this)
        abuseService.getAbuseCases(childIdx)

    }


    //학대 정황 기록 가져오기 (디테일한 것 제외)
    @SuppressLint("SimpleDateFormat")
    override fun onGetAbuseSuccess(code: Int, result: ArrayList<RecordData>) {
        recordList = result

        val formatter = SimpleDateFormat("yyyy년 M월 d일")
        for(i in 0 until recordList.size) {
            val date = formatter.parse(recordList[i].abuseDate)
            binding.calendarView.setSelectedDate(date)
        }

        Log.d("recordList",recordList.toString())

        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
        Log.d("RECORD-DATA",result.toString())
    }

    override fun abuseNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }


    override fun onGetAbuseFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }


}
