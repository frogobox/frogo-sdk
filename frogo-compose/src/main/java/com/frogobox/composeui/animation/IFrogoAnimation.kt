package com.frogobox.composeui.animation

import android.animation.AnimatorSet


/*
 * Created by faisalamir on 16/03/22
 * FrogoAnimation
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface IFrogoAnimation {

    fun setAnimation(animatorSet: AnimatorSet)

    fun setDuration(duration: Long)

    fun setRepeated()

    fun setRepeated(repeatCount: Int)

    fun setRepeatModeReverse()

    fun setRepeatModeRestart()

    fun start()

}