package com.frogobox.composeui.animation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import com.frogobox.composeui.R

object FrogoSingleAnimation {

    private const val TAG = "FrogoSingleAnimation"

    private fun Context.findActivity(): Activity? {
        var current: Context? = this
        while (current is ContextWrapper) {
            if (current is Activity) return current
            current = current.baseContext
        }
        return current as? Activity
    }

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
            } catch (e: Exception) {
                Log.d(TAG, "overridePendingTransition reflection failed: ${e.message}")
            }
        }
    }

    fun slideLeft(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.slide_left_enter,
            R.anim.slide_left_exit
        )
    }

    fun slideRight(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    fun slideDown(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.slide_down_enter,
            R.anim.slide_down_exit
        )
    }

    fun slideUp(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.slide_up_enter,
            R.anim.slide_up_exit
        )
    }

    fun zoom(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.zoom_enter,
            R.anim.zoom_exit
        )
    }

    fun fade(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.fade_enter,
            R.anim.fade_exit
        )
    }

    fun windmill(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.windmill_enter,
            R.anim.windmill_exit
        )
    }

    fun spin(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.spin_enter,
            R.anim.spin_exit
        )
    }

    fun diagonal(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.diagonal_right_enter,
            R.anim.diagonal_right_exit
        )
    }

    fun split(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.split_enter,
            R.anim.split_exit
        )
    }

    fun shrink(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.shrink_enter,
            R.anim.shrink_exit
        )
    }

    fun card(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.card_enter,
            R.anim.card_exit
        )
    }

    fun inAndOut(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.in_out_enter,
            R.anim.in_out_exit
        )
    }

    fun swipeLeft(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.swipe_left_enter,
            R.anim.swipe_left_exit
        )
    }

    fun swipeRight(context: Context) {
        context.findActivity()?.applyCustomTransition(
            R.anim.swipe_right_enter,
            R.anim.swipe_right_exit
        )
    }
}