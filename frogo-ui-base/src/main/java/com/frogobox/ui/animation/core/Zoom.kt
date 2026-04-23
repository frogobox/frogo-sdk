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
Java version of the Zoom class
https://github.com/gayankuruppu/android-view-animations-java/blob/master/library/src/main/java/render/animations/Zoom.java
*/

object Zoom : IZoom {

    override fun In(view: View): AnimatorSet {

        val object1 = ObjectAnimator.ofFloat(view, "scaleX", 0.45f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleY", 0.45f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3)
        }

    }

    override fun InDown(view: View): AnimatorSet {

        val bottom = -view.bottom.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "translationY", bottom, 60f, 0f)
        val object4 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InLeft(view: View): AnimatorSet {

        val right = -view.right.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "translationX", right, 48f, 0f)
        val object4 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InRight(view: View): AnimatorSet {

        val width = -view.width.toFloat()
        val right = -view.paddingRight.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "translationX", width + right, -48f, 0f)
        val object4 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InUp(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.height - view.top).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f)
        val object4 = ObjectAnimator.ofFloat(view, "translationY", distance, -60f, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    /*
    Out
     */

    override fun Out(view: View): AnimatorSet {

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.3f, 0f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.3f, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3)
        }
    }

    override fun OutDown(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.height - view.top).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.475f, 0.1f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.475f, 0.1f)
        val object4 = ObjectAnimator.ofFloat(view, "translationY", 0f, -60f, distance)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }


    override fun OutLeft(view: View): AnimatorSet {

        val right = -view.right.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.475f, 0.1f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.475f, 0.1f)
        val object4 = ObjectAnimator.ofFloat(view, "translationX", 0f, 42f, right)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun OutRight(view: View): AnimatorSet {

        val parent = view.parent as ViewGroup
        val distance = (parent.width - parent.left).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.475f, 0.1f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.475f, 0.1f)
        val object4 = ObjectAnimator.ofFloat(view, "translationX", 0f, -42f, distance)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun OutUp(view: View): AnimatorSet {

        val bottom = -view.bottom.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.475f, 0.1f)
        val object3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.475f, 0.1f)
        val object4 = ObjectAnimator.ofFloat(view, "translationY", 0f, 60f, bottom)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

}