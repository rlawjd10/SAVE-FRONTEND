package com.umc.save.Locker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemAddRecordBinding
import com.umc.save.databinding.ItemChildBinding
import com.umc.save.databinding.ItemSuspectBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.SimpleDateFormat

class SuspectRVAdapter (private val suspectList : ArrayList<Suspect>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    val context: Context,

    interface MyItemClickListener {
        fun onItemClick(suspect: Suspect)
        fun onItemClickAdd()
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == suspectList.size) {
            R.layout.item_add
        } else {
            R.layout.item_suspect
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_suspect -> {
                val view = ItemSuspectBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderSuspect(view)
            }
            else -> {
                val view = ItemAddBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderAdd(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == suspectList.size) {
            (holder as ViewHolderAdd).bind()
            (holder as ViewHolderAdd).itemView.setOnClickListener{  mItemClickListener.onItemClickAdd()}
            } else {
            (holder as ViewHolderSuspect).bind(suspectList[position])
            (holder as ViewHolderSuspect).itemView.setOnClickListener{  mItemClickListener.onItemClick(suspectList[position])}
        }

    }

    override fun getItemCount(): Int = suspectList.size + 1

    inner class ViewHolderSuspect(val binding: ItemSuspectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(suspect: Suspect) {

            val unknown = "-"
            var suspectInfo: String

            val gender: String = when (suspect.suspectGender) {
                "male" -> "남자"
                "female" -> "여자"
                else -> "성별 모름"
            }

            when (gender) {
                "남자" -> {
                    binding.suspectInfoImage.setImageResource(R.drawable.ilst_male_01)
                }
                "여자" -> {
                    binding.suspectInfoImage.setImageResource(R.drawable.ilst_female_01)
                }
                else -> {
                    binding.suspectInfoImage.setImageResource(R.drawable.fragment_white_background)
                }
            }

            suspectInfo = gender + "/" + suspect.suspectAge

            if(suspect.suspectAddress != null) {
                suspectInfo = suspectInfo + "/" + suspect.suspectAddress
            }

            if(suspect.suspectDetailAddress != null) {
                suspectInfo = suspectInfo + "/" + suspect.suspectDetailAddress
            }
            if (suspect.suspectName == null) {
                binding.suspectInfoNameTv.text = unknown
            } else {
                binding.suspectInfoNameTv.text = suspect.suspectName
            }

            binding.suspectInfoSpecificTv.text = suspectInfo
            binding.suspectInfoRelationshipTv.text = suspect.relationship
        }
    }

    inner class ViewHolderAdd(val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

}
