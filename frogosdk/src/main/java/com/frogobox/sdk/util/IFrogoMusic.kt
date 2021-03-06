package com.frogobox.sdk.util

import android.content.Context

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

    fun playMusic(context: Context, musicFile: Int)

    fun stopMusic()

    fun pauseMusic()

}