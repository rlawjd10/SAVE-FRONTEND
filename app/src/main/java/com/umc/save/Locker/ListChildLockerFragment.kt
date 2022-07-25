package com.umc.save.Locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.databinding.FragmentLockerChildListBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class ListChildLockerFragment : Fragment() {
    lateinit var binding : FragmentLockerChildListBinding
    var currentPosition = 0
    private var recordList = ArrayList<RecordData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerChildListBinding.inflate(inflater,container,false)


        recordList.apply {
            add(RecordData(1,Date(2022,7,5), Time(2,3,0),"인천광역시 연수구 송도동","신체학대",Date(2022,3,2)))
            add(RecordData(1,Date(2021,4,3), Time(3,3,0),"인천광역시 연수구 동춘동","정서학대",Date(2021,3,12)))

        }

        val childRecordRVAdapter = ChildRecordRVAdapter(recordList)
        binding.recordListRv.adapter = childRecordRVAdapter
        binding.recordListRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        return binding.root
    }

}
