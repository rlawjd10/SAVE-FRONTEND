package com.umc.save.Home.option

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.save.databinding.ItemProfileBinding

class ProfileRVAdapter: RecyclerView.Adapter<ProfileRVAdapter.MyViewHolder>() {

    var itemList = mutableListOf<ProfileData>()

    inner class MyViewHolder(private val viewBinding: ItemProfileBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(profileData: ProfileData) {
            Glide.with(itemView.context)
                .load(profileData.image)
                .into(viewBinding.homeEditProfileIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}