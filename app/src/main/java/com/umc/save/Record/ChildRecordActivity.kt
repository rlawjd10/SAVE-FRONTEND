package com.umc.save.Record

import android.content.Intent
import android.graphics.Color
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.save.R
import com.umc.save.Record.Auth.AbuseSituation.abuse_var
import com.umc.save.Record.Auth.ChildRecord.*
import com.umc.save.Record.RecordDetail.DetailEtcActivity
import com.umc.save.Sign.Auth.userIdx_var
import com.umc.save.databinding.ActivityChildRecordBinding
import com.umc.save.databinding.ActivityOffenderRecordBinding
import com.umc.save.databinding.FragmentRecordMainBinding


class childRecord_var {
    object abuse {
        var childName : String = ""
        var childAge_1 : String = ""
        var childAge_2 : String = ""
        var address_base : String = ""
        var address_detail : String = ""
    }
}

class ChildRecordActivity : AppCompatActivity(), ChildRecordResult {
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

                binding.recordChildAgeNS.setHintTextColor(Color.parseColor("#FF7F61"))
                binding.txtFlow.setTextColor(Color.parseColor("#FF7F61"))
                binding.txtSae.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordChildAgeNS.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)

            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordChildAgeNS.isEnabled = false

                binding.recordChildAgeNS.setHintTextColor(Color.parseColor("#B5B5B5"))
                binding.txtSae.setTextColor(Color.parseColor("#B5B5B5"))
                binding.txtFlow.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordChildAgeNS.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }


        }

        binding.recordChildName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                childRecord_var.abuse.childName = binding.recordChildName.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordChildName.text.equals("")
                    && (binding.childMale.isSelected|| binding.childFemale.isSelected || binding.childDontKnow.isSelected)
                    && !binding.recordChildAge.text.equals("")
                    && !binding.recordChildAddressBase.text.equals(""))
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })

        binding.recordChildAge.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                childRecord_var.abuse.childAge_1 = binding.recordChildAge.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordChildName.text.equals("")
                    && (binding.childMale.isSelected|| binding.childFemale.isSelected || binding.childDontKnow.isSelected)
                    && !binding.recordChildAge.text.equals("")
                    && !binding.recordChildAddressBase.text.equals(""))
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })

        binding.recordChildAgeNS.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                childRecord_var.abuse.childAge_2 = binding.recordChildAgeNS.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordChildName.text.equals("")
                    && (binding.childMale.isSelected|| binding.childFemale.isSelected || binding.childDontKnow.isSelected)
                    && !binding.recordChildAge.text.equals("")
                    && !binding.recordChildAddressBase.text.equals(""))
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })

        binding.recordChildAddressBase.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                childRecord_var.abuse.address_base = binding.recordChildAddressBase.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordChildName.text.equals("")
                        && (binding.childMale.isSelected|| binding.childFemale.isSelected || binding.childDontKnow.isSelected)
                        && !binding.recordChildAge.text.equals("")
                        && !binding.recordChildAddressBase.text.equals(""))
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })

        binding.recordChildAddressDetail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                childRecord_var.abuse.address_detail = binding.recordChildAddressDetail.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!binding.recordChildName.text.equals("")
                    && (binding.childMale.isSelected|| binding.childFemale.isSelected || binding.childDontKnow.isSelected)
                    && !binding.recordChildAge.text.equals("")
                    && !binding.recordChildAddressBase.text.equals(""))
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_red_background)
                else{
                    binding.recordDone.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }

        })


        binding.recordDone.setOnClickListener{
            if(binding.recordChildName.text.toString().trim().isEmpty())
                Toast.makeText(this, "아동 이름을 기록해주세요", Toast.LENGTH_SHORT).show()
            else{
                if(!binding.childMale.isSelected && !binding.childFemale.isSelected && !binding.childDontKnow.isSelected)
                    Toast.makeText(this, "아동 성별을 기록해주세요", Toast.LENGTH_SHORT).show()
                else{
                    if(binding.recordChildAge.text.toString().trim().isEmpty())
                        Toast.makeText(this, "아동 나이을 입력해주세요", Toast.LENGTH_SHORT).show()
                    else {
                        if (binding.recordChildAddressBase.text.toString().trim().isEmpty())

                            Toast.makeText(this, "아동 주소를 입력해주세요", Toast.LENGTH_SHORT).show()
                        else{
                            save()
                        }
                    }
                }
            }
        }
    }

    private fun getChild() : Child {
        var userIdx = userIdx_var.UserIdx.UserIdx
        val childName : String = childRecord_var.abuse.childName
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

        var childAge : String = childRecord_var.abuse.childAge_1
        if(binding.ageNotSureBtn.isSelected)
            childAge = childAge + "세 ~ " + childRecord_var.abuse.childAge_2 + "세"

        val childAdress : String = childRecord_var.abuse.address_base

        val childDetailAdress : String = childRecord_var.abuse.address_detail

        Log.d("childName =============================== ", childName)

        return Child(userIdx, childName, isCertain, childGender, childAge, childAdress, childDetailAdress)
    }

    private fun save() {
        val childRecordService = ChildRecordService()
        childRecordService.setRecordResult(this)
        childRecordService.record(getChild())
    }

    override fun recordSuccess(result : Result) {

        childidx_var.childIdx.childIdx = result.childIdx

        Log.d("변환 값 ==========================", childidx_var.childIdx.childIdx.toString())
        Toast.makeText(this, "아동 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "아동 기록 성공.")

        val intent = Intent(this, OffenderRecordActivity::class.java)
        finish()
        startActivity(intent)

    }

    override fun NeedUserIdx(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildName(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildGender(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildAge(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildAddress(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun UserDontExist(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
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