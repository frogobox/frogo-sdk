package com.frogobox.sdk.util

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
interface IFrogoMusic {

    fun getMusicPlayer(): MediaPlayer

    fun playMusic()

    fun playMusic(isLooping: Boolean)

    fun stopMusic()

    fun pauseMusic()

}