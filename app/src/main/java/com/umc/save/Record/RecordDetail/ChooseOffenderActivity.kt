package com.umc.save.Record.RecordDetail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.R
import com.umc.save.Record.Suspect
import com.umc.save.Record.SuspectRVAdapter
import com.umc.save.databinding.ActivityChooseOffenderBinding
import java.util.*
import kotlin.collections.ArrayList

class ChooseOffenderActivity : AppCompatActivity() {
    lateinit var binding : ActivityChooseOffenderBinding
    private var suspectList= ArrayList<Suspect>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseOffenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        val suspectRVAdapter = SuspectRVAdapter(suspectList)
        binding.offenderListRv.adapter = suspectRVAdapter
        binding.offenderListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        suspectRVAdapter.setMyItemClickListener(object:SuspectRVAdapter.MyItemClickListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(suspect: Suspect) {
                suspect.isSelected = !suspect.isSelected

                Log.d("suspect changed",suspect.isSelected.toString())
            }
        } )



        suspectList.apply {
            add(Suspect(1, "홍길동", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "어린이집 교사", Date(2022,3,2)))
            add(Suspect(2, "", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "어린이집 원장", Date(2022,3,2)))
            add(Suspect(3, "백길동", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "친부", Date(2022,3,2)))
            add(Suspect(1, "", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "A", Date(2022,3,2)))
            add(Suspect(1, "백길동", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "B", Date(2022,3,2)))
            add(Suspect(1, "오달봉", 1,"남","40",")", "ㅇㄹㄴㄻ",
                "C", Date(2022,3,2)))
        }


        binding.nextBtn.setOnClickListener{
            startActivity(Intent(this, AbuseTypeActivity::class.java))
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
