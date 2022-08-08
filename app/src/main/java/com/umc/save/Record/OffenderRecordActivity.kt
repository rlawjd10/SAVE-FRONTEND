package com.umc.save.Record

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.umc.save.Locker.LockerFragment
import com.umc.save.R
import com.umc.save.databinding.ActivityOffenderRecordBinding

class OffenderRecordActivity : AppCompatActivity() {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0
    var father = 0
    var mother = 0

    lateinit var binding: ActivityOffenderRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffenderRecordBinding.inflate(layoutInflater)
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
                binding.recordOffenderAgeNS.isEnabled = true
                binding.txtSae.setTextColor(Color.parseColor("#FF7F61"))
                binding.txtFlow.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordOffenderAgeNS.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordOffenderAgeNS.isEnabled = false
                binding.txtSae.setTextColor(Color.parseColor("#B5B5B5"))
                binding.txtFlow.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordOffenderAgeNS.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }

        binding.recordAdd.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

        // 학대행위자 성별
        binding.offenderMale.setOnClickListener{
            male++
            if(male % 2 != 0 ) {
                if(binding.offenderFemale.isSelected) {
                    binding.offenderFemale.isSelected = false
                    female++
                }
                if(binding.offenderDontKnow.isSelected) {
                    binding.offenderDontKnow.isSelected = false
                    dontK++
                }

                binding.offenderMale.isSelected = true
            } else{
                binding.offenderMale.isSelected = false
            }
        }

        binding.offenderFemale.setOnClickListener{
            female++
            if(female % 2 != 0 ) {
                if(binding.offenderMale.isSelected) {
                    binding.offenderMale.isSelected = false
                    male++
                }
                if(binding.offenderDontKnow.isSelected) {
                    binding.offenderDontKnow.isSelected = false
                    dontK++
                }

                binding.offenderFemale.isSelected = true
            } else{
                binding.offenderFemale.isSelected = false
            }
        }
        binding.offenderDontKnow.setOnClickListener{
            dontK++
            if(dontK % 2 != 0 ) {
                if(binding.offenderMale.isSelected) {
                    binding.offenderMale.isSelected = false
                    male++
                }
                if(binding.offenderFemale.isSelected) {
                    binding.offenderFemale.isSelected = false
                    female++
                }
                binding.offenderDontKnow.isSelected = true
            } else{
                binding.offenderDontKnow.isSelected = false
            }
        }


        binding.offenderFather.setOnClickListener{
            father++
            if(father % 2 != 0 ) {
                if(binding.offenderMother.isSelected) {
                    binding.offenderMother.isSelected = false
                    mother++
                }

                binding.offenderFather.isSelected = true
            } else{
                binding.offenderFather.isSelected = false
            }
        }
        binding.offenderMother.setOnClickListener{
            mother++
            if(mother % 2 != 0 ) {
                if(binding.offenderFather.isSelected) {
                    binding.offenderFather.isSelected = false
                    father++
                }
                binding.offenderMother.isSelected = true
            } else{
                binding.offenderMother.isSelected = false
            }
        }


        binding.recordNext.setOnClickListener{
            val recordDoneFragment = RecordDoneFragment()
            val fragment : Fragment? = supportFragmentManager.findFragmentByTag(RecordDoneFragment::class.java.simpleName)
            if(fragment !is RecordDoneFragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.layout_content, recordDoneFragment, recordDoneFragment::class.java.simpleName)
                    .commit()
            }
            binding.content.visibility = View.GONE
        }
    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "학대 행위자"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }
}