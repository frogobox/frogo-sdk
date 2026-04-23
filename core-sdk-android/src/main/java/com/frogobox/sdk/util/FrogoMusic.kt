package com.frogobox.sdk.util

import android.content.Context
import android.media.MediaPlayer

/*
 * Created by faisalamir on 29/08/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
class FrogoMusic(
    private val context: Context,
    private val musicFile: Int
) : IFrogoMusic {

    private var musicPlayer: MediaPlayer? = MediaPlayer.create(context, musicFile)

    private fun ensurePlayer(): MediaPlayer {
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(context, musicFile)
        }
        return musicPlayer ?: throw IllegalStateException("Failed to create MediaPlayer")
    }

    override fun getMusicPlayer(): MediaPlayer {
        return ensurePlayer()
    }

    override fun playMusic(isLooping: Boolean) {
        ensurePlayer().apply {
            this.isLooping = isLooping
        }.start()
    }

    override fun playMusic() {
        ensurePlayer().start()
    }

    override fun stopMusic() {
        musicPlayer?.stop()
        musicPlayer?.release()
        musicPlayer = null
    }

    override fun pauseMusic() {
        musicPlayer?.pause()
    }

}