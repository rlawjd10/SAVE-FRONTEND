package com.umc.save.Locker

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.Listener
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.umc.save.R
import com.umc.save.databinding.ItemRecordingBinding
import java.util.concurrent.TimeUnit

class TestRecordingFragment : Fragment() {
    lateinit var binding : ItemRecordingBinding
    private var player : ExoPlayer? = null
    var url = "https://dby56rj67jahx.cloudfront.net/recording/192470e0-2fcd-49d1-b2a0-6105b864a930.m4a"
//    var url="https://dby56rj67jahx.cloudfront.net/recording/958e5d70-91d2-402e-8a0e-f1fe49d45683.mp3"

    private val timeRunnable = Runnable {
        updateTime()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemRecordingBinding.inflate(inflater,container,false)

        initPlayer()


        binding.exoPlay.setOnClickListener {
            initAudio(url)
            play()
        }

        binding.exoPause.setOnClickListener {
            pause()
        }

        return binding.root
    }


    private fun initPlayer() {
        context?.let {
            player = ExoPlayer.Builder(it).build()
        }
        binding.audioPlayerPv.player = player
        binding.let { binding ->
            player?.addListener(object:Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    if(isPlaying) {
                        binding.exoPlay.visibility = View.GONE
                        binding.exoPause.visibility = View.VISIBLE
                        binding.exoPosition.visibility = View.VISIBLE
                        binding.exoDuration.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_light_gray))
                        binding.exoPosition.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.recordingTitleTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    } else {
                        binding.exoPlay.visibility = View.VISIBLE
                        binding.exoPause.visibility = View.GONE
                        binding.exoPosition.visibility = View.GONE
                        binding.exoDuration.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        binding.recordingTitleTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    }

                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    updateTime()

                }

            })
        }

    }

    private fun updateTime() {
        val player = this.player ?: return
        val duration = if(player.duration >= 0) player.duration else 0
        val position = player.currentPosition

        updateTimeUi(duration,position)

        val state = player.playbackState

        view?.removeCallbacks(timeRunnable)
        if (state != Player.STATE_IDLE && state !=Player.STATE_ENDED) {
            view?.postDelayed(timeRunnable,1000)
        }
    }

    private fun updateTimeUi(duration:Long, position: Long) {
        binding?.let { binding ->
            binding.exoDuration.text = String.format("%02d:%02d",
                TimeUnit.MINUTES.convert(duration,TimeUnit.MILLISECONDS),
                (duration / 1000) % 60)
            binding.exoPosition.text = String.format("%02d:%02d",
                TimeUnit.MINUTES.convert(position,TimeUnit.MILLISECONDS),
                (position / 1000) % 60)
        }
    }

    private fun initAudio(url: String) {
        context?.let {
            val dataSourceFactory = DefaultDataSource.Factory(it)
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            player?.setMediaSource(mediaSource)
        }

    }


    private fun play() {
        player?.prepare()
        player?.play()
    }


    private fun pause() {
        player?.pause()
//        player?.release()
//        player = null
    }


}

