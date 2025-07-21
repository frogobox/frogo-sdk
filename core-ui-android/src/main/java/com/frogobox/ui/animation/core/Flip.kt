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

/*
Java version of the Flip class
https://github.com/gayankuruppu/android-view-animations-java/blob/master/library/src/main/java/render/animations/Flip.java
*/


object Flip : IFlip {

    override fun InX(view: View): AnimatorSet {
        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "rotationX", 90f, -15f, 15f, 0f)
        return AnimatorSet().apply {
            playTogether(object1, object2)
        }
    }

    override fun InY(view: View): AnimatorSet {
        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "rotationY", 90f, -15f, 15f, 0f)
        return AnimatorSet().apply {
            playTogether(object1, object2)
        }
    }

    override fun OutX(view: View): AnimatorSet {
        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotationX", 0f, 90f)
        return AnimatorSet().apply {
            playTogether(object1, object2)
        }
    }

    override fun OutY(view: View): AnimatorSet {
        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f)
        return AnimatorSet().apply {
            playTogether(object1, object2)
        }
    }

}