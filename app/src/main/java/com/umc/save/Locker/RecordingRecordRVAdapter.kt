package com.umc.save.Locker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.umc.save.R
import com.umc.save.databinding.ItemRecordingBinding

class RecordingRecordRVAdapter(private val recordingList : ArrayList<Recording>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var player : ExoPlayer? = null

    interface MyItemClickListener {
        fun onItemClick(recording: Recording)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = ItemRecordingBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolderRecording(view)


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as ViewHolderRecording).bind(recordingList[position])
            (holder as ViewHolderRecording).itemView.findViewById<ImageView>(R.id.recording_play_iv)
                .setOnClickListener {
                    recordingList[position].isPlaying = !recordingList[position].isPlaying
                    play(recordingList[position])
                    }

    }


//    private fun playAudio(recording: Recording) {
//
////        val music = Context.resources.getIdentifier(recording.location, "raw", activity?.packageName)
//        mediaPlayer = MediaPlayer.create(context,)
//
//    }


    override fun getItemCount(): Int = recordingList.size

    inner class ViewHolderRecording(val binding: ItemRecordingBinding) : RecyclerView.ViewHolder(binding.root) {

//        recording.isPlaying = !recording.isPlaying

        @SuppressLint("ResourceType")
        fun bind(recording: Recording) {

            if (recording.isPlaying) {
                binding.recordingPlayIv.visibility = View.GONE
                binding.recordingPauseIv.visibility = View.VISIBLE
                binding.recordingTimeNowTv.text =
                    String.format("%02d:%02d", recording.second / 60, recording.second % 60)
            }
            else {
                binding.recordingPlayIv.visibility = View.VISIBLE
                binding.recordingPauseIv.visibility = View.GONE
                binding.recordingTimeNowTv.visibility = View.GONE
            }
            binding.recordingTitleTv.text = recording.recordingTitle
            binding.recordingLengthTv.text = String.format("%02d:%02d",recording.length / 60,recording.length % 60)
        }

    }

//    private fun initPlayer() {
//        player = ExoPlayer.Builder(this).build()
//        binding.videoPlayerPv.player = player
//
//    }


    private fun play(recording: Recording) {



    }

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
