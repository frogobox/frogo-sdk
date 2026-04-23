package com.frogobox.ui.animation.core

import android.animation.AnimatorSet
import android.view.View


/*
 * Created by faisalamir on 16/03/22
 * FrogoAnimation
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface IAttention {

    fun Bounce(view: View): AnimatorSet

    fun Flash(view: View): AnimatorSet

    fun Pulse(view: View): AnimatorSet

    fun Ruberband(view: View): AnimatorSet

    fun Shake(view: View): AnimatorSet

    fun Standup(view: View): AnimatorSet

    fun Swing(view: View): AnimatorSet

    fun Tada(view: View): AnimatorSet

    fun Wave(view: View): AnimatorSet

    fun Wobble(view: View): AnimatorSet

}