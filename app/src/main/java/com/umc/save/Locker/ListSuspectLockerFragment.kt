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
import kotlin.Int as Int

class ListSuspectLockerFragment : Fragment(), SuspectsView {

    lateinit var binding: FragmentLockerSuspectListBinding
    var currentPosition = 0
    private var suspectList= ArrayList<Suspect>()
    private var gson : Gson = Gson()
    lateinit var child : Child

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSuspectListBinding.inflate(inflater,container,false)

        val childJson = arguments?.getString("child")
        child = gson.fromJson(childJson,Child::class.java)


        binding.suspectEditTv.setOnClickListener {
            openDeleteActivity(child.childIdx)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()

        getSuspects(child.childIdx)
    }

    private fun openDeleteActivity(childIdx: Int) {
        val intent = Intent(context, SuspectDeleteLockerActivity::class.java)
        intent.putExtra("childIdx",childIdx)
        startActivity(intent)
    }


    private fun openRecordActivity() {
        val intent = Intent(context, OffenderRecordActivity::class.java)
        startActivity(intent)
    }

    private fun getSuspects(childIdx : Int) {

        val suspectsService = SuspectsService()

        suspectsService.setSuspectsView(this)
        suspectsService.getSuspects(childIdx)

    }


    private fun initRecyclerView(result : ArrayList<Suspect>) {
        val suspectRVAdapter = SuspectRVAdapter(result)
        binding.suspectListRv.adapter = suspectRVAdapter
        binding.suspectListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        suspectRVAdapter.setMyItemClickListener(object: SuspectRVAdapter.MyItemClickListener {
            override fun onItemClick(suspect: Suspect) {

            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

    }

    override fun onGetSuspectsSuccess(code: Int, result: ArrayList<Suspect>) {
        initRecyclerView(result)
        Log.d("GET-SUCCESS",result.toString())
    }

    override fun suspectNotExist(code: Int, message: String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetSuspectsFailure(code: Int, message: String) {
        Log.d("GET-FAILURE",message)
    }
}
