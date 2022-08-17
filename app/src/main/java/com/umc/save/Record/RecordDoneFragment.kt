package com.umc.save.Record

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.umc.save.Home.HomeFragment
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Home.option.HomeSettingsFragment
import com.umc.save.Locker.LockerFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
import com.umc.save.databinding.FragmentRecordDoneBinding
import java.util.concurrent.locks.Lock

class RecordDoneFragment : Fragment() {
    lateinit var binding: FragmentRecordDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecordDoneBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initActionBar()

        binding.moveToStorage.setOnClickListener {
            (context as RecordDoneActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, LockerFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.recordGoOn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ChooseOffenderActivity::class.java)
                startActivity(intent)
            }
        }
    }

//    private fun initActionBar() {
//
//        binding.mainActionbar.actionMainHomeIv.setOnClickListener {
//            (context as RecordDoneActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, HomeFragment())
//                .addToBackStack(null)
//                .commitAllowingStateLoss()
//        }
//        binding.mainActionbar.actionMainAlarmIv.setOnClickListener {
//            (context as RecordDoneActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, HomeAlarmFragment())
//                .addToBackStack(null)
//                .commitAllowingStateLoss()
//        }
//        binding.mainActionbar.actionMainSettingsIv.setOnClickListener {
//            (context as RecordDoneActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, HomeSettingsFragment())
//                .addToBackStack(null)
//                .commitAllowingStateLoss()
//        }
//
//
//    }


}