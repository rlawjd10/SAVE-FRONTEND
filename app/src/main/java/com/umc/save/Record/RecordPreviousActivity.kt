package com.umc.save.Record

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
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
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
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
            startActivity(Intent(this, ChooseOffenderActivity::class.java))
        }
        getPreviousChild()
    }

    private fun getPreviousChild(){
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

                if(RecordPreRVAdapter.clicked)
                    binding.nextBtn.setBackgroundColor(Color.parseColor("#FF7F61"))
                else
                    binding.nextBtn.setBackgroundColor(Color.parseColor("#B5B5B5"))
            }
        })
        Toast.makeText(this, "아동 불러오기 성공", Toast.LENGTH_SHORT).show()
    }

    override fun getChildFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}