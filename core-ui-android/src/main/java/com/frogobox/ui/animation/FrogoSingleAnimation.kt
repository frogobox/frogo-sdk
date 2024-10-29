package com.frogobox.ui.animation

import android.app.Activity
import android.content.Context
import com.frogobox.ui.R

object FrogoSingleAnimation {
    fun slideLeft(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.slide_left_enter,
            R.anim.slide_left_exit
        )
    }

    fun slideRight(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    fun slideDown(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.slide_down_enter,
            R.anim.slide_down_exit
        )
    }

    fun slideUp(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
    }

    fun zoom(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
    }

    fun fade(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.fade_enter, R.anim.fade_exit)
    }

    fun windmill(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.windmill_enter, R.anim.windmill_exit)
    }

    fun spin(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.spin_enter, R.anim.spin_exit)
    }

    fun diagonal(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.diagonal_right_enter,
            R.anim.diagonal_right_exit
        )
    }

    fun split(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.split_enter, R.anim.split_exit)
    }

    fun shrink(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.shrink_enter, R.anim.shrink_exit)
    }

    fun card(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.card_enter, R.anim.card_exit)
    }

    fun inAndOut(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.in_out_enter, R.anim.in_out_exit)
    }

    fun swipeLeft(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.swipe_left_enter,
            R.anim.swipe_left_exit
        )
    }

    fun swipeRight(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.swipe_right_enter,
            R.anim.swipe_right_exit
        )
    }
}