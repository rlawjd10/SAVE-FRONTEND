package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.umc.save.Locker.LockerFragment
import com.umc.save.R
import com.umc.save.databinding.ActivityOffenderRecordBinding

class OffenderRecordActivity : AppCompatActivity() {
    var name_num = 0
    var age_num = 0
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
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordOffenderAgeNS.isEnabled = false
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }

        binding.recordAdd.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

        binding.recordNext.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.offender_frm, RecordDoneFragment())
                .commit()

//            startActivity(Intent(this, RecordDoneFragment::class.java))
        }
    }
}