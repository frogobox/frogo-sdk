/*
 * MIT License
 *
 * Copyright (c) 2019 Gayan Kuruppu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/*
 * Created by faisalamir on 23/08/21
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

package com.frogobox.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator

class FrogoAnimation : IFrogoAnimation {

    private var du: Long = 1000
    private var isRepeated = false
    private var usingRepeatMode = false
    private var mRepeatCount = ObjectAnimator.INFINITE
    private var mRepeatMode = ObjectAnimator.RESTART

    private lateinit var animatorSet: AnimatorSet

    override fun setAnimation(animatorSet: AnimatorSet) {
        this.animatorSet = animatorSet
    }

    override fun setDuration(duration: Long) {
        this.du = duration
    }

    override fun setRepeated() {
        isRepeated = true
    }

    override fun setRepeated(repeatCount: Int) {
        isRepeated = true
        mRepeatCount = repeatCount
    }

    override fun setRepeatModeReverse() {
        usingRepeatMode = true
        mRepeatMode = ObjectAnimator.REVERSE
    }

    override fun setRepeatModeRestart() {
        usingRepeatMode = true
    }

    override fun start() {
        animatorSet.apply {
            duration = du
            interpolator = LinearInterpolator()
            childAnimations.forEach {
                val animator = it as ObjectAnimator
                if (isRepeated) {
                    animator.repeatCount = mRepeatCount
                }
                if (usingRepeatMode) {
                    animator.repeatMode = mRepeatMode
                }
            }
        }.start()
    }

}