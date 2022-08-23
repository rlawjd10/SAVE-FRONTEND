package com.umc.save.Locker.Recording

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.umc.save.Locker.data.Recording
import com.umc.save.R
import com.umc.save.databinding.ItemRecordingDefaultBinding

class RecordingRecordRVAdapter( private val recordingList : ArrayList<Recording>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var player : ExoPlayer? = null

    interface MyItemClickListener {
        fun onItemClick(recording: Recording)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = ItemRecordingDefaultBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ViewHolder).bind(recordingList[position])
        (holder as ViewHolder).itemView.findViewById<ImageView>(R.id.recording_play_iv)
            .setOnClickListener { mItemClickListener.onItemClick(recordingList[position]) }

    }

    override fun getItemCount(): Int = recordingList.size

    inner class ViewHolder(val binding: ItemRecordingDefaultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recording: Recording) {
            binding.recordingTitleTv.text = recording.recordingTitle
        }
    }



}