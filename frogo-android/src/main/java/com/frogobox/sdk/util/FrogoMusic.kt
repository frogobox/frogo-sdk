package com.frogobox.sdk.util

import android.content.Context
import android.media.MediaPlayer

/*
 * Created by faisalamir on 29/08/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
class FrogoMusic(
    context: Context,
    private val musicFile: Int
) : IFrogoMusic {

    private val appContext: Context = context.applicationContext
    private var musicPlayer: MediaPlayer? = MediaPlayer.create(appContext, musicFile)

    private fun ensurePlayer(): MediaPlayer {
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(appContext, musicFile)
        }
        return musicPlayer ?: throw IllegalStateException("Failed to create MediaPlayer")
    }

    override fun getMusicPlayer(): MediaPlayer {
        return ensurePlayer()
    }

    override fun playMusic(isLooping: Boolean) {
        try {
            ensurePlayer().apply {
                this.isLooping = isLooping
            }.start()
        } catch (e: Exception) {
            com.frogobox.sdk.log.FLog.e("FrogoMusic: failed to play music - ${e.message.orEmpty()}")
        }
    }

    override fun playMusic() {
        playMusic(false)
    }

    override fun stopMusic() {
        try {
            if (musicPlayer?.isPlaying == true) {
                musicPlayer?.stop()
            }
        } catch (e: Exception) {
            com.frogobox.sdk.log.FLog.e("FrogoMusic: error stopping player - ${e.message.orEmpty()}")
        } finally {
            try {
                musicPlayer?.release()
            } catch (_: Exception) {}
            musicPlayer = null
        }
    }

    override fun pauseMusic() {
        try {
            if (musicPlayer?.isPlaying == true) {
                musicPlayer?.pause()
            }
        } catch (e: Exception) {
            com.frogobox.sdk.log.FLog.e("FrogoMusic: error pausing player - ${e.message.orEmpty()}")
        }
    }

}