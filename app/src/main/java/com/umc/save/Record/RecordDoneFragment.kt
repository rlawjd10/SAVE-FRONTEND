package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.save.R
import com.umc.save.databinding.FragmentRecordDoneBinding

class RecordDoneFragment : Fragment() {
    lateinit var binding: FragmentRecordDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_done, container, false)


    }


}