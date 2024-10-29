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

interface IRotate {

    fun In(view: View): AnimatorSet

    fun InDownLeft(view: View): AnimatorSet

    fun InDownRight(view: View): AnimatorSet

    fun InUpLeft(view: View): AnimatorSet

    fun InUpRight(view: View): AnimatorSet

    fun Out(view: View): AnimatorSet

    fun OutDownLeft(view: View): AnimatorSet

    fun OutDownRight(view: View): AnimatorSet

    fun OutUpLeft(view: View): AnimatorSet

    fun OutUpRight(view: View): AnimatorSet

}