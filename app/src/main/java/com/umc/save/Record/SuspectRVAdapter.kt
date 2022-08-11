package com.umc.save.Record

import com.umc.save.Record.Auth.SuspectGet.Suspect
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.umc.save.R
import com.umc.save.databinding.ItemOffenderBinding

class SuspectRVAdapter(private val suspectList : ArrayList<Suspect>) :
    RecyclerView.Adapter<SuspectRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(suspect: Suspect)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemOffenderBinding = ItemOffenderBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suspectList[position])
        holder.itemView.setOnClickListener{
            suspectList[position].isSelected = !suspectList[position].isSelected
            holder.bind(suspectList[position])
        }

    }

    override fun getItemCount(): Int = suspectList.size


    inner class ViewHolder(val binding: ItemOffenderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(suspect: Suspect) {
            var suspectRel : String? = suspect.relationship

            binding.itemOffenderInfoRelTv.text = suspectRel
            binding.itemOffenderInfoNameTv.text = suspect.suspectName


            if (suspect.isSelected) {
                binding.itemOffenderInfoIb.setBackgroundColor(Color.parseColor("#FF7F61"))
                binding.itemOffenderInfoRelTv.setTextColor(Color.parseColor("#FFFFFFFF"))
            } else {
                binding.itemOffenderInfoIb.setBackgroundColor(Color.parseColor("#F5F5F5"))
                binding.itemOffenderInfoRelTv.setTextColor(Color.parseColor("#FF7F61"))
            }




        }
    }
}