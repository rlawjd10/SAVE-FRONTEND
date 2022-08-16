package com.umc.save.Home

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ActionbarInnerPageTopBinding
import com.umc.save.databinding.ActionbarMainTopBinding
import com.umc.save.databinding.FragmentHomeStatisBinding
import com.umc.save.databinding.ItemStatisBinding

class StatisHomeFragment : Fragment() {

   lateinit var binding: FragmentHomeStatisBinding
   lateinit var adapter: StatisRVAdapter

   val mDatas = mutableListOf<StatisData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeStatisBinding.inflate(inflater, container, false)

        initStatisRecyclerView()
        return binding.root
    }

    //리사이클러뷰 data 넘기기
    fun initStatisRecyclerView() {

        val adapter = StatisRVAdapter()

        adapter.datalist = mDatas.apply {
            add(StatisData("신고접수 건수", R.drawable.img_stat_01))
            add(StatisData("아동학대 건수", R.drawable.img_stat_02))
            add(StatisData("아동학대사례 유형", R.drawable.img_stat_03))
            add(StatisData("피해아동 성별", R.drawable.img_stat_04))
            add(StatisData("피해아동 연령", R.drawable.img_stat_05))
            add(StatisData("피해아동 가족유형", R.drawable.img_stat_06))
            add(StatisData("학대행위자 성별", R.drawable.img_stat_07))
            add(StatisData("학대행위자 연령", R.drawable.img_stat_08))
            add(StatisData("학대행위자와 피해아동과의 관계", R.drawable.img_stat_09))
        }
        binding.homeStatisRv.adapter = adapter
        binding.homeStatisRv.layoutManager = LinearLayoutManager(this.context)
    }

}