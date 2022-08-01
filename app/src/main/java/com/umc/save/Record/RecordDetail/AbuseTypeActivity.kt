package com.umc.save.Record.RecordDetail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.save.R
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.ActivityAbuseTypeBinding

class AbuseTypeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAbuseTypeBinding
    var e_num = 0
    var p_num = 0
    var s_num = 0
    var n_num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbuseTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.physicalAbuseBtn.setOnClickListener{
            p_num++
            if(p_num % 2 != 0 ) {
                binding.physicalAbuseBtn.isSelected = true
            } else{
                binding.physicalAbuseBtn.isSelected = false
            }
        }

        binding.emotionalAbuseBtn.setOnClickListener{
            e_num++
            if(e_num % 2 != 0 ) {
                binding.emotionalAbuseBtn.isSelected = true
            } else{
                binding.emotionalAbuseBtn.isSelected = false
            }
        }

        binding.sexualAbuseBtn.setOnClickListener{
            s_num++
            if(s_num % 2 != 0 ) {
                binding.sexualAbuseBtn.isSelected = true
            } else{
                binding.sexualAbuseBtn.isSelected = false
            }
        }

        binding.neglectAbuseBtn.setOnClickListener{
            n_num++
            if(n_num % 2 != 0 ) {
                binding.neglectAbuseBtn.isSelected = true
            } else{
                binding.neglectAbuseBtn.isSelected = false
            }
        }

        binding.nextBtn.setOnClickListener{
            startActivity(Intent(this, CalendarTimePlaceActivity::class.java))
        }

    }
}