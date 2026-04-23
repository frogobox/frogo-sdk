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

interface IBounce {

    fun In(view: View): AnimatorSet

    fun InLeft(view: View): AnimatorSet

    fun InRight(view: View): AnimatorSet

    fun InUp(view: View): AnimatorSet

    fun InDown(view: View): AnimatorSet

}