package com.umc.save.Record

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.save.Record.RecordDetail.AbuseTypeActivity
import com.umc.save.databinding.FragmentRecordDoneBinding

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
        binding.moveToStorage.setOnClickListener {
//            activity?.let {
//                val intent = Intent(context, ChildRecordActivity::class.java)
//                startActivity(intent)
//            }
        }

        binding.recordGoOn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, AbuseTypeActivity::class.java)
                startActivity(intent)
            }
        }
    }




}