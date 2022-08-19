package com.umc.save.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.FragmentHomeNewsBinding

class NewsHomeFragment : Fragment() {

    lateinit var binding: FragmentHomeNewsBinding
    lateinit var adapter: NewsRVAdapter

    val mDates = mutableListOf<NewsData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeNewsBinding.inflate(inflater, container, false)

        initNewsRecyclerView()
        initActionBar()
        return binding.root
    }

    //액션바
    private fun initActionBar() {

        binding.mainActionbar.appbarPageNameTv.text = "관련 뉴스"

        binding.mainActionbar.appbarBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .popBackStack()
        }

    }


    //리사이클러뷰 data 담기
    fun initNewsRecyclerView() {
        val adapter = NewsRVAdapter()

        adapter.itemList = mDates.apply {
            add(NewsData(R.drawable.home_news_01, "2022. 08. 17", "책으로 때리고 발로 '툭툭'…아동 학대한 복지관 언어치료사", "https://www.hankyung.com/society/article/2022081704557"))
            add(NewsData(R.drawable.home_news_02, "2022. 08. 15", "3남매에게 고문 수준 학대행위 15차례 친부, 집행유예", "https://newsis.com/view/?id=NISX20220815_0001978500&cID=10810&pID=10800#"))
            add(NewsData(R.drawable.home_news_06, "2022. 08. 11", "'화성 입양아 학대살인' 양부 징역 22년 확정", "https://www.yonhapnewstv.co.kr/news/MYH20220811015600038?input=1825m"))
            add(NewsData(R.drawable.home_news_05, "2022. 08. 10", "검찰, 과외 아동 폭행 대학생에 징역 3년 구형", "https://biz.chosun.com/topics/topics_social/2022/08/10/EF6IXXYDANF4PJEIRX7K5DPGCI/?utm_source=naver&utm_medium=original&utm_campaign=biz"))
            add(NewsData(R.drawable.home_news_04, "2022. 08. 09", "'남매 30차례 상습학대'…30대 아버지 징역 5년 구형", "https://www.mbn.co.kr/news/society/4820449"))
            add(NewsData(R.drawable.home_news_07, "2022. 08. 09", "'\"아동 참여권 늘려달라\"…전국 아동총회 9~11일 개최", "https://newsis.com/view/?id=NISX20220808_0001971666&cID=10201&pID=10200"))
            add(NewsData(R.drawable.home_news_03, "2022. 08. 07", "툭하면 때린 어린이집…원아 9명 수백차례 학대 드러나", "https://www.donga.com/news/article/all/20220807/114848733/1"))
        }
        binding.homeNewsRv.adapter = adapter
        binding.homeNewsRv.layoutManager = LinearLayoutManager(this.context)
    }

}