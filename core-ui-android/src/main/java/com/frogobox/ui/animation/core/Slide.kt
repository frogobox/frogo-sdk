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

package com.frogobox.ui.animation.core

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup

/*
Java version of the Slide class
https://github.com/gayankuruppu/android-view-animations-java/blob/master/library/src/main/java/render/animations/Slide.java
*/

object Slide : ISlide {

    override fun InDown(view: View): AnimatorSet {

        val distance = (view.top + view.height).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "translationY", -distance, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun InLeft(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.width - view.left).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "translationX", -distance, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun InRight(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.width - view.left).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "translationX", distance, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun InUp(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.height - view.top).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "translationY", distance, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun OutDown(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.height - view.top).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "translationY", 0f, distance)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun OutLeft(view: View): AnimatorSet {

        val right = view.right.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "translationX", 0f, -right)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun OutRight(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.width - view.left).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "translationX", 0f, distance)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun OutUp(view: View): AnimatorSet {

        val bottom = -view.bottom.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "translationY", 0f, bottom)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

}