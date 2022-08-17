package com.umc.save.Record.RecordDetail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.umc.save.R
import com.umc.save.Record.Auth.AbuseSituation.AbuseSituation
import com.umc.save.Record.Auth.AbuseSituation.abuse_var
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

        initActionBar()

        binding.physicalAbuseBtn.setOnClickListener{
            p_num++
            if(p_num % 2 != 0 ) {
                if(binding.emotionalAbuseBtn.isSelected){
                    binding.emotionalAbuseBtn.isSelected = false
                    e_num++
                }
                if(binding.sexualAbuseBtn.isSelected){
                    binding.sexualAbuseBtn.isSelected = false
                    s_num++
                }
                if(binding.neglectAbuseBtn.isSelected){
                    binding.neglectAbuseBtn.isSelected = false
                    n_num++
                }
                binding.physicalAbuseBtn.isSelected = true
                abuse_var.abuse.a_type = "신체적 학대"
            } else{
                binding.physicalAbuseBtn.isSelected = false
                abuse_var.abuse.a_type = ""
            }

            Log.d("abuse type ======================================================", abuse_var.abuse.a_type)
            if(!abuse_var.abuse.a_type.equals(""))
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_red_background)
            else{
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }

        binding.emotionalAbuseBtn.setOnClickListener{
            e_num++
            if(e_num % 2 != 0 ) {
                if(binding.physicalAbuseBtn.isSelected){
                    binding.physicalAbuseBtn.isSelected = false
                    p_num++
                }
                if(binding.sexualAbuseBtn.isSelected){
                    binding.sexualAbuseBtn.isSelected = false
                    s_num++
                }
                if(binding.neglectAbuseBtn.isSelected){
                    binding.neglectAbuseBtn.isSelected = false
                    n_num++
                }
                binding.emotionalAbuseBtn.isSelected = true
                abuse_var.abuse.a_type = "정신적 학대"
            } else{
                binding.emotionalAbuseBtn.isSelected = false
                abuse_var.abuse.a_type = ""
            }

            Log.d("abuse type ======================================================", abuse_var.abuse.a_type)
            if(!abuse_var.abuse.a_type.equals(""))
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_red_background)
            else{
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }

        binding.sexualAbuseBtn.setOnClickListener{
            s_num++
            if(s_num % 2 != 0 ) {
                if(binding.physicalAbuseBtn.isSelected){
                    binding.physicalAbuseBtn.isSelected = false
                    p_num++
                }
                if(binding.emotionalAbuseBtn.isSelected){
                    binding.emotionalAbuseBtn.isSelected = false
                    e_num++
                }
                if(binding.neglectAbuseBtn.isSelected){
                    binding.neglectAbuseBtn.isSelected = false
                    n_num++
                }
                binding.sexualAbuseBtn.isSelected = true
                abuse_var.abuse.a_type = "성적 학대"
            } else{
                binding.sexualAbuseBtn.isSelected = false
                abuse_var.abuse.a_type = ""
            }

            Log.d("abuse type ======================================================", abuse_var.abuse.a_type)
            if(!abuse_var.abuse.a_type.equals(""))
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_red_background)
            else{
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }

        binding.neglectAbuseBtn.setOnClickListener{
            n_num++
            if(n_num % 2 != 0 ) {
                if(binding.physicalAbuseBtn.isSelected){
                    binding.physicalAbuseBtn.isSelected = false
                    p_num++
                }
                if(binding.emotionalAbuseBtn.isSelected){
                    binding.emotionalAbuseBtn.isSelected = false
                    e_num++
                }
                if(binding.sexualAbuseBtn.isSelected){
                    binding.sexualAbuseBtn.isSelected = false
                    s_num++
                }
                binding.neglectAbuseBtn.isSelected = true
                abuse_var.abuse.a_type = "방관"
            } else{
                binding.neglectAbuseBtn.isSelected = false
                abuse_var.abuse.a_type = ""
            }

            Log.d("abuse type ======================================================", abuse_var.abuse.a_type)
            if(!abuse_var.abuse.a_type.equals(""))
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_red_background)
            else{
                binding.nextBtn.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }



        binding.nextBtn.setOnClickListener{
            if(abuse_var.abuse.a_type.equals(""))
                Toast.makeText(this, "학대 유형을 선택해주세요", Toast.LENGTH_SHORT).show()
            else {
                startActivity(Intent(this, CalendarTimePlaceActivity::class.java))
            }
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