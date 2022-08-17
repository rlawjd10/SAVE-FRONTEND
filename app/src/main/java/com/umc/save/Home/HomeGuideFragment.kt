package com.umc.save.Home

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeGuideBinding
import com.umc.save.databinding.FragmentHomeStatisBinding


class GuideHomeFragment : Fragment() {

    lateinit var binding: FragmentHomeGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeGuideBinding.inflate(inflater, container, false)

        binding.guideSuspectCardView.setOnClickListener {
            changeClick(binding.homeGuideHiddenLayout, binding.guideSuspectCardView, binding.homeGuideFixedIv)
        }
        binding.guideReportCardView.setOnClickListener {
            changeClick(binding.homeGuideReportHiddenLayout, binding.guideReportCardView, binding.homeGuideReportFixedIv)
        }
        binding.guideChildCardView.setOnClickListener {
            changeClick(binding.homeGuideChildHiddenLayout, binding.guideChildCardView, binding.homeGuideChildFixedIv)
        }


        initActionBar()
        return binding.root
    }

    //view는 hidden, view2는 fixed card view, imageview는 toggle
    private fun changeClick(view: ViewGroup, view2: ViewGroup, imageView: ImageView) {
        if (view.visibility == View.VISIBLE) {
            /*TransitionManager.beginDelayedTransition(view2, AutoTransition())*/
            imageView.setImageResource(R.drawable.icn_bottom_normal)
            view.visibility = View.GONE
        } else {
            TransitionManager.beginDelayedTransition(view2, AutoTransition())
            view.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.icn_top_normal)
        }
    }

    private fun initActionBar() {

        binding.mainActionbar.appbarPageNameTv.text = "신고 가이드"

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }

    }
}