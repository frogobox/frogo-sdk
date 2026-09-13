package com.frogobox.ui.animation

import android.app.Activity
import android.content.Context
import android.os.Build
import com.frogobox.ui.R

object FrogoSingleAnimation {

    private fun Activity.applyCustomTransition(enterAnim: Int, exitAnim: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, enterAnim, exitAnim)
        } else {
            try {
                Activity::class.java.getMethod(
                    "overridePendingTransition",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                ).invoke(this, enterAnim, exitAnim)
            } catch (_: Exception) {
            }
        }
    }

    fun slideLeft(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.slide_left_enter,
            R.anim.slide_left_exit
        )
    }

    fun slideRight(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    fun slideDown(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.slide_down_enter,
            R.anim.slide_down_exit
        )
    }

    fun slideUp(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.slide_up_enter,
            R.anim.slide_up_exit
        )
    }

    fun zoom(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.zoom_enter,
            R.anim.zoom_exit
        )
    }

    fun fade(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.fade_enter,
            R.anim.fade_exit
        )
    }

    fun windmill(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.windmill_enter,
            R.anim.windmill_exit
        )
    }

    fun spin(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.spin_enter,
            R.anim.spin_exit
        )
    }

    fun diagonal(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.diagonal_right_enter,
            R.anim.diagonal_right_exit
        )
    }

    fun split(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.split_enter,
            R.anim.split_exit
        )
    }

    fun shrink(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.shrink_enter,
            R.anim.shrink_exit
        )
    }

    fun card(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.card_enter,
            R.anim.card_exit
        )
    }

    fun inAndOut(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.in_out_enter,
            R.anim.in_out_exit
        )
    }

    fun swipeLeft(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.swipe_left_enter,
            R.anim.swipe_left_exit
        )
    }

    fun swipeRight(context: Context) {
        (context as Activity).applyCustomTransition(
            R.anim.swipe_right_enter,
            R.anim.swipe_right_exit
        )
    }
}