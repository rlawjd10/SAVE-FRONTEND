package com.umc.save.Locker.Video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.save.Locker.data.Video
import com.umc.save.R
import com.umc.save.databinding.ItemVideoBinding

class VideoRecordRVAdapter(private val videoList : ArrayList<Video>) : RecyclerView.Adapter<VideoRecordRVAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(video: Video)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemVideoBinding = ItemVideoBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videoList[position])
        holder.itemView.findViewById<ImageView>(R.id.video_play).setOnClickListener{ mItemClickListener.onItemClick(videoList[position]) }
    }
    override fun getItemCount(): Int = videoList.size

    inner class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image : ImageView = itemView.findViewById<ImageView>(R.id.thumbnail_iv)

        fun bind(video: Video) {
            Glide.with(itemView)
                .load(video.thumb)
                .into(image)

        }
    }

}

