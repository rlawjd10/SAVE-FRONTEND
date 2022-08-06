package com.umc.save.Locker

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Context
import android.icu.text.AlphabeticIndex
import android.media.MediaPlayer
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.databinding.ItemRecordingDefaultBinding
import com.umc.save.databinding.ItemRecordingPlayingBinding
import java.util.logging.Handler

class RecordingRecordRVAdapter(private val recordingList : ArrayList<Recording>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mediaPlayer : MediaPlayer? = null

    interface MyItemClickListener {
        fun onItemClick(recording: Recording)
//        fun onItemClickAdd()
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {

        return if (!recordingList[position].isPlaying) {
            R.layout.item_recording_default
        } else {
            R.layout.item_recording_playing
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

//        val view = ItemRecordingDefaultBinding.inflate(LayoutInflater.from(viewGroup.context),
//            viewGroup, false)
//        return ViewHolderRecording(view)

        return when (viewType) {
            R.layout.item_recording_default -> {
                val view = ItemRecordingDefaultBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderRecording(view)
            }
            else -> {
                val view = ItemRecordingPlayingBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderRecordingPlaying(view)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            (holder as ViewHolderRecording).bind(recordingList[position])
//            (holder as ViewHolderRecording).itemView.findViewById<ImageView>(R.id.recording_play_iv)
//                .setOnClickListener { mItemClickListener.onItemClick(recordingList[position])
//                    notifyItemChanged(position)}

        if (!recordingList[position].isPlaying) {
            (holder as ViewHolderRecording).bind(recordingList[position])
            (holder as ViewHolderRecording).itemView.findViewById<ImageView>(R.id.recording_play_iv)
                .setOnClickListener { mItemClickListener.onItemClick(recordingList[position])
                notifyItemChanged(position)}
        } else {
            (holder as ViewHolderRecordingPlaying).bind(recordingList[position])
            (holder as ViewHolderRecordingPlaying).itemView.findViewById<ImageView>(R.id.recording_pause_iv)
                .setOnClickListener { mItemClickListener.onItemClick(recordingList[position])
                    notifyItemChanged(position)}
        }

    }


//    private fun playAudio(recording: Recording) {
//
////        val music = Context.resources.getIdentifier(recording.location, "raw", activity?.packageName)
//        mediaPlayer = MediaPlayer.create(context,)
//
//    }


    override fun getItemCount(): Int = recordingList.size

    inner class ViewHolderRecording(val binding: ItemRecordingDefaultBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun bind(recording: Recording) {

//            if (recording.isPlaying) {
//                binding.recordingPlayIv.visibility = View.GONE
//                binding.recordingPauseIv.visibility = View.VISIBLE
//            }
//            else {
//                binding.recordingPlayIv.visibility = View.VISIBLE
//                binding.recordingPauseIv.visibility = View.GONE
//            }

            binding.recordingTitleTv.text = recording.recordingTitle
            binding.recordingLengthTv.text = String.format("%02d:%02d",recording.length / 60,recording.length % 60)
        }

    }

    inner class ViewHolderRecordingPlaying(val binding: ItemRecordingPlayingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recording: Recording) {
            binding.recordingTitleTv.text = recording.recordingTitle
            binding.recordingLengthTv.text =
                String.format("%02d:%02d", recording.length / 60, recording.length % 60)
            binding.recordingTimeNowTv.text =
                String.format("%02d:%02d", recording.second / 60, recording.second % 60)
            binding.recordingProgressbarPb.progress = (recording.second * 1000 / recording.length)

        }
    }


//    private fun updateProgress(recording: Recording) {
//
//    }

//    inner class Timer(var recording: Recording):Thread(){
//        //        private val length: Int, var isPlaying: Boolean = true
//        private var second : Int = 0
//        private var mills : Float = 0f
//        private val recordingRVAdapter = RecordingRecordRVAdapter(recordingList)
//
//
//        @SuppressLint("NotifyDataSetChanged")
//        override fun run() {
//            super.run()
//            try {
//                while (true) {
//                    if (second >= recording.length){
//                        break
//                    }
//
//                    if (recording.isPlaying){
//                        sleep(50)
//                        mills += 50
//
//                        .runOnUiThread {
//                            recording.progress = ((mills/recording.length)*100).toInt()
//                            recordingRVAdapter.notifyDataSetChanged()
////                            binding.recordingProgress.progress = ((mills/playTime)*100).toInt()
//                        }
//                        if (mills % 1000 == 0f){
//                            activity?.runOnUiThread{
//                                recording.second = second
////                                binding.songStartTimeTv.text = String.format("%02d:%02d",second / 60, second % 60)
//                                recordingRVAdapter.notifyDataSetChanged()
//                            }
//                            second++
//                        }
//                    }
//                }
//            } catch (e : InterruptedException) {
//                Log.d("Song","스레드가 죽었습니다. ${e.message}")
//            }
//
//        }
//    }


}
