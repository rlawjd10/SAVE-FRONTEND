package com.umc.save.Locker

import android.icu.text.AlphabeticIndex
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.R
import com.umc.save.databinding.ItemRecordingDefaultBinding

class RecordingRecordRVAdapter(private val recordingList : ArrayList<Recording>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(recording: Recording)
//        fun onItemClickAdd()
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (position == recordingList.size) {
//            R.layout.item_add
//        } else {
//            R.layout.item_child
//        }
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = ItemRecordingDefaultBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolderRecording(view)

//        return when (viewType) {
//            R.layout.item_child -> {
//                val view = ItemRecordingDefaultBinding.inflate(LayoutInflater.from(viewGroup.context),
//                    viewGroup, false)
//                ViewHolderRecording(view)
//            }
//            else -> {
//                val view = ItemAddBinding.inflate(LayoutInflater.from(viewGroup.context),
//                    viewGroup, false)
//                ViewHolderAdd(view)
//            }
//        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ViewHolderRecording).bind(recordingList[position])
        (holder as ViewHolderRecording).itemView.findViewById<ImageView>(R.id.recording_play_iv)
            .setOnClickListener { mItemClickListener.onItemClick(recordingList[position]) }


    }

    override fun getItemCount(): Int = recordingList.size

    inner class ViewHolderRecording(val binding: ItemRecordingDefaultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recording: Recording) {
            binding.recordingTitleTv.text = recording.recordingTitle
            binding.recordingLengthTv.text = recording.length.toString()
        }
    }

}
