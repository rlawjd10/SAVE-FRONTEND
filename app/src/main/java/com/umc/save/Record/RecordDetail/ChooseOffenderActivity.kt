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
import com.umc.save.Record.Auth.ChildGet.ChildGetService
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectGet.Suspect
import com.umc.save.Record.Auth.SuspectGet.SuspectGetResult
import com.umc.save.Record.Auth.SuspectGet.SuspectGetService
import com.umc.save.Record.SuspectRVAdapter
import com.umc.save.databinding.ActivityChooseOffenderBinding
import java.util.*
import kotlin.collections.ArrayList

class ChooseOffenderActivity : AppCompatActivity(), SuspectGetResult {
    lateinit var binding : ActivityChooseOffenderBinding
    private var suspectList= ArrayList<Suspect>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseOffenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        chooseSuspect()

        binding.nextBtn.setOnClickListener{
            startActivity(Intent(this, AbuseTypeActivity::class.java))
        }
    }

    private fun chooseSuspect(){
//        val childRecordService = ChildRecordService()
//        childRecordService.setRecordResult(this)
//        childRecordService.record(getChild())
        val suspectGetService = SuspectGetService()
        suspectGetService.setSuspectGetResult(this)
        suspectGetService.getSuspect(childidx_var.childIdx.childIdx)
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
        result: java.util.ArrayList<Suspect>
    ) {
        suspectList.addAll(result)

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
        TODO("Not yet implemented")
    }

    override fun getChildFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}
