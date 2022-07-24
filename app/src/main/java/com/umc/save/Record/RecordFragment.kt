package com.umc.save.Record

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.fragment_record_main, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recordNewChild.setOnClickListener{
            activity?.let {
                val intent = Intent(context, ChildRecordActivity::class.java)
                startActivity(intent)
            }
        }

        // 보관함으로 이동하는 코드 추가 //




    }


}