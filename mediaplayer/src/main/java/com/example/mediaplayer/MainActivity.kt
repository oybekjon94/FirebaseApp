package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
            player?.start()
        }
        binding.pauseBtn.setOnClickListener {
            player?.apply {
                if (isPlaying){
                    pause()
                }else{
                    start()
                }
            }
        }
        binding.stopBtn.setOnClickListener {
            player?.release()
            player = null
        }
    }
}