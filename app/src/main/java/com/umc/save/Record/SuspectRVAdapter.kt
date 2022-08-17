package com.umc.save.Record

import com.umc.save.Record.Auth.SuspectGet.Suspect
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.Record.RecordDetail.ChooseOffenderActivity
import com.umc.save.databinding.ActivityChooseOffenderBinding
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

    var get_suspectIdx : Int = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suspectList[position])
        holder.itemView.setOnClickListener{
            get_suspectIdx = suspectList[position].suspectIdx
            suspectIdx_var.suspectIdx.suspectIdx = get_suspectIdx
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
                get_suspectIdx = suspectList[position].suspectIdx
                suspectIdx_var.suspectIdx.suspectIdx = get_suspectIdx
                binding.itemOffenderInfoIb.setBackgroundColor(Color.parseColor("#FF7F61"))
                binding.itemOffenderInfoRelTv.setTextColor(Color.parseColor("#FFFFFFFF"))
                binding.itemCheckBtn.setImageResource(R.drawable.icn_check_01_on)
            } else {
                binding.itemOffenderInfoIb.setBackgroundColor(Color.parseColor("#F5F5F5"))
                binding.itemOffenderInfoRelTv.setTextColor(Color.parseColor("#FF7F61"))
                binding.itemCheckBtn.setImageResource(R.drawable.icn_check_01_off)
            }
        }
    }
}