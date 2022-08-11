package com.umc.save.Record

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectRecord.Result
import com.umc.save.Record.Auth.SuspectRecord.SuspectRecordResult
import com.umc.save.Record.Auth.SuspectRecord.SuspectRecordService
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.databinding.ActivityChildRecordBinding
import com.umc.save.databinding.ActivityOffenderRecordBinding

class OffenderRecordActivity : AppCompatActivity(), SuspectRecordResult {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0
    var father = 0
    var mother = 0


    lateinit var binding: ActivityOffenderRecordBinding
    lateinit var binding2 : ActivityChildRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffenderRecordBinding.inflate(layoutInflater)
        binding2 = ActivityChildRecordBinding.inflate(layoutInflater)
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
            save()
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

        // 학대행위자 성별
        binding.offenderMale.setOnClickListener{
            male++
            Log.d("받아온 childIdx값 ==================================== ", childidx_var.childIdx.childIdx.toString())

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
            save()

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

    private fun save(){

        if (binding.offenderMale.isSelected.toString().isEmpty()
            && binding.offenderFemale.isSelected.toString().isEmpty()
            && binding.offenderDontKnow.isSelected.toString().isEmpty()) {
            Toast.makeText(this, "학대 행위자의 성별을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.recordOffenderAge.text.toString().isEmpty()) {
            Toast.makeText(this, "학대 행위자의 나이를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.offenderFather.isSelected.toString().isEmpty()
            && binding.offenderMother.isSelected.toString().isEmpty()
            && binding.recordRelation.text.toString().isEmpty()) {
            Toast.makeText(this, "아동과의 관계를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val suspectRecordService = SuspectRecordService()
        suspectRecordService.setRecordResult(this)
        suspectRecordService.record(getSuspect())


    }

    private fun getSuspect() : Suspect {

        var childIdx : Int = childidx_var.childIdx.childIdx


        val suspectName : String = binding.recordOffenderName.text.toString()

        var suspectGender : String = "여"
        if(binding.offenderMale.isSelected)
            suspectGender = "male"
        if(binding.offenderFemale.isSelected)
            suspectGender = "female"
        if(binding.offenderDontKnow.isSelected)
            suspectGender = "unknown"

        var suspectAge : String = binding.recordOffenderAge.text.toString()
        if(binding.ageNotSureBtn.isSelected)
            suspectAge = suspectAge + "세 ~ " + binding.recordOffenderAge.text.toString() + "세 추정"

        val suspectAdress : String = binding.recordOffenderAddressBase.text.toString()
        val suspectDetailAdress : String = binding.recordOffenderAddressDetail.text.toString()

        var relationWithChild : String = ""
        if(binding.offenderFather.isSelected)
            relationWithChild = "부"
        else if (binding.offenderMother.isSelected)
            relationWithChild = "모"
        if(!binding.offenderMother.isSelected && !binding.offenderFather.isSelected)
            relationWithChild = binding.recordRelation.text.toString()

        val suspectEtc : String = binding.recordOffenderEtc.text.toString()

        return Suspect(childIdx, suspectName, suspectGender, suspectAge, suspectAdress, suspectDetailAdress, relationWithChild, suspectEtc )
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

    override fun recordSuccess(result : Result) {
        suspectIdx_var.suspectIdx.suspectIdx = result.suspectIdx
        Toast.makeText(this, "학대 행위자 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 행위자 기록 성공.")
    }

    override fun recordFailure() {
        Toast.makeText(this, "학대 행위자 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 행위자 기록 실패.")
        Log.d("childIdx값 ==================================== ", getSuspect().childIdx.toString())

    }
}