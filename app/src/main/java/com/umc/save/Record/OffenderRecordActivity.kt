package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                binding.txtFlow.setTextColor(R.color.dark_red)
                binding.recordOffenderAgeNS.setTextColor(R.color.dark_red)
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordOffenderAgeNS.isEnabled = false
                binding.txtFlow.setTextColor(R.color.dark_gray)
                binding.recordOffenderAgeNS.setTextColor(R.color.dark_gray)
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
                binding.offenderMale.isSelected = true
            } else{
                binding.offenderMale.isSelected = false
            }
        }

        binding.offenderFemale.setOnClickListener{
            female++
            if(female % 2 != 0 ) {
                binding.offenderFemale.isSelected = true
            } else{
                binding.offenderFemale.isSelected = false
            }
        }
        binding.offenderDontKnow.setOnClickListener{
            dontK++
            if(dontK % 2 != 0 ) {
                binding.offenderDontKnow.isSelected = true
            } else{
                binding.offenderDontKnow.isSelected = false
            }
        }


        binding.offenderFather.setOnClickListener{
            father++
            if(father % 2 != 0 ) {
                binding.offenderFather.isSelected = true
            } else{
                binding.offenderFather.isSelected = false
            }
        }
        binding.offenderMother.setOnClickListener{
            mother++
            if(mother % 2 != 0 ) {
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
}