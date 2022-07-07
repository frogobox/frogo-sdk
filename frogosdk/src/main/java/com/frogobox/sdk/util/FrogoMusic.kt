package com.frogobox.sdk.util

import android.content.Context
import android.media.MediaPlayer
import com.frogobox.sdk.ext.showLogDebug

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
object FrogoMusic : IFrogoMusic {

    val TAG: String = FrogoMusic::class.java.simpleName

    private lateinit var mediaPlayer: MediaPlayer

    override fun playMusic(context: Context, musicFile: Int) {
        mediaPlayer = MediaPlayer.create(context, musicFile)
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        showLogDebug("$TAG : Playing Music : $musicFile")
    }

    override fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.release()
        showLogDebug("$TAG : Music Has Been Stoped")
    }

    override fun pauseMusic() {
        mediaPlayer.pause()
        showLogDebug("$TAG : Music Has Been Paused")
    }

}