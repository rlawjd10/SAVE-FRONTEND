package com.umc.save.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

        //관련 링크로
        //조사 및 응급처치
        binding.homeActionReportBtn.setOnClickListener {
            val url = Intent(Intent.ACTION_VIEW, Uri.parse("https://easylaw.go.kr/CSP/CnpClsMainBtr.laf?popMenu=ov&csmSeq=1125&ccfNo=2&cciNo=1&cnpClsNo=2"))
            startActivity(url)
        }
        //사후 관리
        binding.homeActionShieldBtn.setOnClickListener {
            val url = Intent(Intent.ACTION_VIEW, Uri.parse("https://easylaw.go.kr/CSP/CnpClsMainBtr.laf?popMenu=ov&csmSeq=1125&ccfNo=2&cciNo=1&cnpClsNo=3"))
            startActivity(url)
        }
        //관련 법령
       binding.homeActionLawBtn.setOnClickListener {
           val url = Intent(Intent.ACTION_VIEW, Uri.parse("https://easylaw.go.kr/CSP/CnpClsMainBtr.laf?popMenu=ov&csmSeq=1125&ccfNo=3&cciNo=1&cnpClsNo=1"))
           startActivity(url)
       }

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