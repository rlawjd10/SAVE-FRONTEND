package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.Record.OffenderRecordActivity
import com.umc.save.databinding.FragmentLockerBinding
import com.umc.save.databinding.FragmentLockerSuspectListBinding
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class ListSuspectLockerFragment : Fragment() {
    lateinit var binding: FragmentLockerSuspectListBinding
    var currentPosition = 0
    private var suspectList= ArrayList<Suspect>()
    private var gson : Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSuspectListBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        val child = gson.fromJson(childJson,Child::class.java)


        suspectList.apply {
            add(Suspect(1,"홍길동","여",
                "32","","","모", Date(2022-1900,1,2)))
            add(Suspect(2,"","남",
                "42","","","부", Date(2022-1900,2,2)))
            add(Suspect(3,"박길동","여",
                "22","","","모", Date(2022-1900,3,2)))
            add(Suspect(4,"최길동","남",
                "32","","","부", Date(2022-1900,4,2)))
            add(Suspect(5,"오길동","여",
                "32","","","모", Date(2022-1900,1,2)))

        }

        val suspectRVAdapter = SuspectRVAdapter(suspectList)
        binding.suspectListRv.adapter = suspectRVAdapter
        binding.suspectListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)


        suspectRVAdapter.setMyItemClickListener(object: SuspectRVAdapter.MyItemClickListener {
            override fun onItemClick(suspect: Suspect) {
                changeRecordChildLockerFragment(suspect)
            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

        return binding.root
    }


    private fun changeRecordChildLockerFragment(suspect: Suspect) {
//        (context as MainActivity).supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.main_frm, InfoChildLockerFragment().apply {
//                arguments = Bundle().apply {
//                    val gson = Gson()
//                    val childJson = gson.toJson(child)
//                    putString("child",childJson)
//                }
//            })
//            .addToBackStack(null)
//            .commitAllowingStateLoss()
    }

    private fun openRecordActivity() {
        val intent = Intent(context, OffenderRecordActivity::class.java)
        startActivity(intent)
    }

}
