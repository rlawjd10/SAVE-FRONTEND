package com.umc.save.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeActionBinding
import com.umc.save.databinding.FragmentHomeBinding


class ActionHomeFragment : Fragment() {

    lateinit var binding: FragmentHomeActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeActionBinding.inflate(inflater,container,false)

        initActionBar()
        // Inflate the layout for this fragment
        return binding.root
    }


    private fun initActionBar() {

        binding.mainActionbar.appbarPageNameTv.text = "조사 ∙ 조치"

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }

    }

}