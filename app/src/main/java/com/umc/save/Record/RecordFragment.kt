package com.umc.save.Record

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.umc.save.Home.HomeFragment
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Home.option.HomeSettingsFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentRecordMainBinding


class RecordFragment : Fragment() {
    lateinit var binding: FragmentRecordMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecordMainBinding.inflate(inflater,container,false)

        initActionBar()

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recordNewChild.setOnClickListener{
            activity?.let {

                val intent = Intent(context, ChildRecordActivity::class.java)
                startActivity(intent)

//                RecordDoneTransaction()
            }
        }

        // 보관함으로 이동하는 코드 추가 //
        binding.recordPreviousChild.setOnClickListener{
            activity?.let {
                val intent = Intent(context, RecordPreviousActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()

        if(recordDoneFragment_call)
            RecordDoneTransaction()
    }

    // Fragment 새로고침
    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {

        Log.d("refreshFragment 호출 ========================= ", "refreshFragment 호출")
        var ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.detach(fragment).attach(fragment).commit()
        Log.d("222 recordDoneFragment_call ========================= ", recordDoneFragment_call.toString())
    }



    fun RecordDoneTransaction(){
        recordDoneFragment_call = !recordDoneFragment_call // 값 되돌려주기
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, RecordDoneFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun initActionBar() {

        binding.mainActionbar.actionMainHomeIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainAlarmIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeAlarmFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.mainActionbar.actionMainSettingsIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeSettingsFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

}