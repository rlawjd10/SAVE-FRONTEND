package com.umc.save.Home

import android.content.Intent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.databinding.ItemStatisBinding
import com.bumptech.glide.Glide
import com.umc.save.R

class StatisRVAdapter: RecyclerView.Adapter<StatisRVAdapter.MyViewHolder>() {

    var datalist = mutableListOf<StatisData>()

    inner class MyViewHolder(private val binding: ItemStatisBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(statisData:StatisData) {
            binding.homeStatisFixedTv.text = statisData.statis_title
            Glide.with(itemView.context)
                .load(statisData.statis_img)
                .into(binding.homeStatisHiddenIv)

            itemView.setOnClickListener {
                if (binding.homeStatisHiddenLayout.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(binding.statisCardView, AutoTransition())
                    binding.homeStatisFixedIv.setImageResource(R.drawable.icn_bottom_normal)
                    binding.homeStatisHiddenLayout.visibility = View.GONE
                } else {
                    TransitionManager.beginDelayedTransition(binding.statisCardView, AutoTransition())
                    binding.homeStatisHiddenLayout.visibility = View.VISIBLE
                    binding.homeStatisFixedIv.setImageResource(R.drawable.icn_top_normal)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ItemStatisBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = 9

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

   /* *//*클릭 인퍼테이스 정의*//*
   interface ItemClickListener{
        fun onClick(view: View,position: Int)
    }
    //를릭 리스너
    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }*/


}
