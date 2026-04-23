package com.frogobox.ui.ext

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.frogobox.ui.animation.FrogoAnimation
import com.frogobox.ui.animation.core.Attention
import com.frogobox.ui.fireworks.ParticleSystem

/**
 * Created by Faisal Amir on 16/01/23
 * Copyright (C) Frogobox
 */

fun View.frogoStartAnimation(isRepeat: Boolean) {
    FrogoAnimation().apply {
        setAnimation(Attention.Ruberband(this@frogoStartAnimation))
        if (isRepeat) {
            setRepeated()
        }
        setDuration(1500)
    }.start()
}

fun View.setOneShotFireWorks(activity: Activity, @DrawableRes drawableRes: Int) {
    ParticleSystem(activity, 50, drawableRes, 1000)
        .setSpeedRange(0.1f, 0.25f)
        .oneShot(this, 100)
}

fun View.setEmitFireWorks(activity: Activity, @DrawableRes drawableRes: Int) {
    ParticleSystem(activity, 50, drawableRes, 1000)
        .setSpeedRange(0.1f, 0.25f)
        .emit(this, 100)
}

fun View.setEmitFireWorks(viewGroup: ViewGroup, @DrawableRes drawableRes: Int) {
    viewGroup.context.getDrawable(drawableRes)?.let {
        ParticleSystem(
            viewGroup,
            50,
            it,
            1000
        )
            .setSpeedRange(0.1f, 0.25f)
            .emit(this, 100)
    }
}