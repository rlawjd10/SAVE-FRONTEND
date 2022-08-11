package com.umc.save.Record

import android.content.Intent
import android.graphics.Color
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.*
import com.umc.save.databinding.ActivityChildRecordBinding
import com.umc.save.databinding.ActivityOffenderRecordBinding


class ChildRecordActivity : AppCompatActivity(), ChildRecordResult {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0

    var get_childIdx = 10

    lateinit var binding: ActivityChildRecordBinding
    lateinit var binding2: ActivityOffenderRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildRecordBinding.inflate(layoutInflater)
        binding2 = ActivityOffenderRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()


        /////// 아동 이름
        binding.nameNotSureBtn.setOnClickListener{
            name_num++
            if(name_num % 2 != 0 ) {
                binding.nameNotSureBtn.isSelected = true
            } else{
                binding.nameNotSureBtn.isSelected = false
            }
        }

        ////////// 아동 성별
        binding.childMale.setOnClickListener{
            male++
            if(male % 2 != 0 ) {
                if(binding.childFemale.isSelected) {
                    binding.childFemale.isSelected = false
                    female++
                }
                if(binding.childDontKnow.isSelected) {
                    binding.childDontKnow.isSelected = false
                    dontK++
                }
                binding.childMale.isSelected = true
            } else{
                binding.childMale.isSelected = false

            }
        }

        binding.childFemale.setOnClickListener{
            female++
            if(female % 2 != 0 ) {
                if(binding.childMale.isSelected) {
                    binding.childMale.isSelected = false
                    male++
                }
                if(binding.childDontKnow.isSelected) {
                    binding.childDontKnow.isSelected = false
                    dontK++
                }

                binding.childFemale.isSelected = true
            } else{
                binding.childFemale.isSelected = false
            }
        }
        binding.childDontKnow.setOnClickListener{
            dontK++
            if(dontK % 2 != 0 ) {
                if(binding.childMale.isSelected) {
                    binding.childMale.isSelected = false
                    male++
                }
                if(binding.childFemale.isSelected) {
                    binding.childFemale.isSelected = false
                    female++
                }
                binding.childDontKnow.isSelected = true
            } else{
                binding.childDontKnow.isSelected = false
            }
        }


        //// 아동 나이

        binding.ageNotSureBtn.setOnClickListener{
            age_num++
            if(age_num % 2 != 0 ) {
                binding.ageNotSureBtn.isSelected = true
                binding.recordChildAgeNS.isEnabled = true

                binding.txtFlow.setTextColor(Color.parseColor("#FF7F61"))
                binding.txtSae.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordChildAgeNS.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)

            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordChildAgeNS.isEnabled = false

                binding.txtSae.setTextColor(Color.parseColor("#B5B5B5"))
                binding.txtFlow.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordChildAgeNS.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }



        binding.recordDone.setOnClickListener{
            save()

            val intent = Intent(this, OffenderRecordActivity::class.java)
            startActivity(intent)
            var childIdx : String = binding.childIdx.text.toString()
//            intent.putExtra("childIdx_send", childIdx)
            Log.d("binding.childIdx 값 11111111 ============================ ", childIdx)
        }
    }

    private fun getChild() : Child {
        var userIdx = 23
        val childName : String = binding.recordChildName.text.toString()
        val isCertain : Boolean
        if(binding.nameNotSureBtn.isSelected) {
            isCertain = true
        }else isCertain = false

        var childGender : String = "여"
        if(binding.childMale.isSelected)
            childGender = "male"
        if(binding.childFemale.isSelected)
            childGender = "female"
        if(binding.childDontKnow.isSelected)
            childGender = "unknown"

        var childAge : String = binding.recordChildAge.text.toString()
        if(binding.ageNotSureBtn.isSelected)
            childAge = childAge + "세 ~ " + binding.recordChildAgeNS.text.toString() + "세"

        val childAdress : String = binding.recordChildAddressBase.text.toString()
        val childDetailAdress : String = binding.recordChildAddressDetail.text.toString()

        return Child(userIdx, childName, isCertain, childGender, childAge, childAdress, childDetailAdress)
    }

    private fun save() {
        if (binding.recordChildName.text.toString().isEmpty()) {
            Toast.makeText(this, "아동의 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.childMale.isSelected.toString().isEmpty()
            && binding.childFemale.isSelected.toString().isEmpty()
            && binding.childDontKnow.isSelected.toString().isEmpty()) {
            Toast.makeText(this, "아동의 성별을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val childRecordService = ChildRecordService()
        childRecordService.setRecordResult(this)
        childRecordService.record(getChild())
    }

    override fun recordSuccess(result : Result) {

        childidx_var.childIdx.childIdx = result.childIdx

        Log.d("변환 값 ==========================", childidx_var.childIdx.childIdx.toString())
        Toast.makeText(this, "아동 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "아동 기록 성공.")
    }


    override fun recordFailure() {
        Toast.makeText(this, "아동 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "아동 기록 실패.")
    }


    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "아동"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }
}