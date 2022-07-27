package com.umc.save.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.homeActionBtn.setOnClickListener {
            changeFragment(ActionHomeFragment())
        }
        binding.homeStatsBtn.setOnClickListener {
            changeFragment(StatisHomeFragment())
        }
        binding.homeGuideBtn.setOnClickListener {
            changeFragment(GuideHomeFragment())
        }
        binding.homeNewsBtn.setOnClickListener {
            changeFragment(NewsHomeFragment())
        }

        return binding.root
    }

    private fun changeFragment(fragment: Fragment) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .disallowAddToBackStack()
            .commit()
    }

}
