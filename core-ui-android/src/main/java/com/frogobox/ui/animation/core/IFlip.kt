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

interface IFlip {

    fun InX(view: View): AnimatorSet

    fun InY(view: View): AnimatorSet

    fun OutX(view: View): AnimatorSet

    fun OutY(view: View): AnimatorSet

}