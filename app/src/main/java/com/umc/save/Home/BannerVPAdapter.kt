package com.umc.save.Home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.save.databinding.ItemBannerBinding
import com.umc.save.databinding.ItemNewsBinding

class BannerVPAdapter: RecyclerView.Adapter<BannerVPAdapter.PagerViewHolder> (){

    var itemList = mutableListOf<BannerData>()

    inner class PagerViewHolder(private val viewBinding: ItemBannerBinding) :RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bannerData: BannerData) {
            viewBinding.bannerItemIv.setImageResource(bannerData.bImg)
            //링크로 이동
            itemView.setOnClickListener {
                var url = Intent(Intent.ACTION_VIEW, Uri.parse(bannerData.bUrl))
                ContextCompat.startActivity(itemView.context, url, null)
            }
        }
    }

    //아이템의 갯수를 임의로 엄청 늘린다.
    //무한으로 스크롤 하기 위함
    override fun getItemCount(): Int =  Int.MAX_VALUE


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val viewBinding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(viewBinding)
    }

    //position에 5를 나눈 나머지 값을 사용한다.
    //무한으로 스크롤 하기 위함
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(itemList[position%5])
    }
}