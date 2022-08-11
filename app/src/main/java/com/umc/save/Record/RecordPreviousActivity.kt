package com.umc.save.Record

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.Record.Auth.ChildGet.Child
import com.umc.save.R
import com.umc.save.Record.Auth.ChildGet.ChildGetResult
import com.umc.save.Record.Auth.ChildGet.ChildGetService
import com.umc.save.databinding.ActivityRecordPreviousBinding
import java.util.*

class RecordPreviousActivity : AppCompatActivity(), ChildGetResult {
    lateinit var binding: ActivityRecordPreviousBinding

    private var childList= ArrayList<Child>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordPreviousBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this, ChildRecordActivity::class.java))
        }

        getPreviousChild()


    }

    private fun getPreviousChild(){
//        val childRecordService = ChildRecordService()
//        childRecordService.setRecordResult(this)
//        childRecordService.record(getChild())
        val childGetService = ChildGetService()
        childGetService.setChildGetResult(this)
        childGetService.getChild(23)
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

    override fun getChildSuccess(
        code: Int,
        result: ArrayList<Child>
    ) {
        childList.addAll(result)


        val RecordPreRVAdapter = RecordPreRVAdapter(childList)

        binding.childListRv.adapter = RecordPreRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        RecordPreRVAdapter.setMyItemClickListener(object: RecordPreRVAdapter.MyItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(child: Child) {
                RecordPreRVAdapter.clicked = !RecordPreRVAdapter.clicked

                Log.d("suspect changed", RecordPreRVAdapter.clicked.toString())

            }

        })
//        for(i in 0 until result.size - 1) {
//            try {
//                childList.add(result[result.size - 1])
//                Log.d("변환 값 ==========================", result.get(result.size).toString())
//            } catch (IndexOutOfBoundsException e ){
//                e.printStrackTrace
//            }
//        }

//        childList.apply {
//            add(
//                Child(1,"양현진",
//                    "여","10","인천광역시 연수구 송도동",
//                    "1000-1202", Date(2022-1900,1,2)
//                )
//            )
//            add(
//                Child(2,"울랄라",
//                    "여","10","서울시 광진구",
//                    "902-1002", Date(2022-1900,1,4)
//                )
//            )
//            add(
//                Child(3,"양땡땡",
//                    "여","10","부산",
//                    "1000-1202", Date(2021-1900,2,3)
//                )
//            )
//            add(
//                Child(1,"양현진",
//                    "여","10","인천광역시 연수구 송도동",
//                    "1000-1202", Date(2022-1900,1,2)
//                )
//            )
//            add(
//                Child(2,"울랄라",
//                    "여","10","서울시 광진구",
//                    "902-1002", Date(2022-1900,1,4)
//                )
//            )
//            add(
//                Child(3,"양땡땡",
//                    "여","10","부산",
//                    "1000-1202", Date(2021-1900,2,3)
//                )
//            )
//        }
        Toast.makeText(this, "아동 불러오기 성공", Toast.LENGTH_SHORT).show()
    }

    override fun getChildFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}