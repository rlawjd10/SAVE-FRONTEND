package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.umc.save.R
import com.umc.save.databinding.ActivityChildRecordBinding


class ChildRecordActivity : AppCompatActivity() {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0

    lateinit var binding: ActivityChildRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.nameNotSureBtn.setOnClickListener{
            name_num++
            if(name_num % 2 != 0 ) {
                binding.nameNotSureBtn.isSelected = true
            } else{
                binding.nameNotSureBtn.isSelected = false
            }
        }

        binding.ageNotSureBtn.setOnClickListener{
            age_num++
            if(age_num % 2 != 0 ) {
                binding.ageNotSureBtn.isSelected = true
                binding.recordChildAgeNS.isEnabled = true

                binding.txtFlow.setTextColor(R.color.dark_red)
                binding.recordChildAgeNS.setTextColor(R.color.dark_red)
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordChildAgeNS.isEnabled = false

                binding.txtFlow.setTextColor(R.color.dark_gray)
                binding.recordChildAgeNS.setTextColor(R.color.dark_gray)
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }

        // 아이 성별
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



        binding.recordDone.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

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