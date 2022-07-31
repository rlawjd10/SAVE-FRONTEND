//package com.umc.save.Locker
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.umc.save.databinding.ItemVideoBinding
//
//class VideoRecordRVAdapter(private val videoList : ArrayList<Video>) : RecyclerView.Adapter<ChildRecordRVAdapter.ViewHolder>() {
//    interface MyItemClickListener {
//        fun onItemClick(recordData: RecordData)
//    }
//
//    private lateinit var mItemClickListener: VideoRecordRVAdapter.MyItemClickListener
//
//    fun setMyItemClickListener(itemClickListener : VideoRecordRVAdapter.MyItemClickListener) {
//        mItemClickListener = itemClickListener
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        val binding: ItemVideoBinding = ItemVideoBinding.inflate(LayoutInflater.from(viewGroup.context),
//            viewGroup, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(videoList[position])
//        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(videoList[position]) }
//    }
//
//    override fun getItemCount(): Int = videoList.size
//
//    inner class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(record: RecordData) {
//
//        }
//    }
//
//}
//
