package com.umc.save.Locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.save.databinding.FragmentLockerChildCalendarBinding
import com.umc.save.databinding.FragmentLockerChildExpandBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class ExpandChildLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerChildExpandBinding
    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildExpandBinding.inflate(inflater,container,false)

        return binding.root
    }
}