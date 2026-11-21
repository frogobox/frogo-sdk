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
Java version of the Rotate class
https://github.com/gayankuruppu/android-view-animations-java/blob/master/library/src/main/java/render/animations/Rotate.java
*/

object Rotate : IRotate {

    override fun In(view: View): AnimatorSet {

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", -200f, 0f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }
    }

    override fun InDownLeft(view: View): AnimatorSet {

        val x = view.paddingLeft.toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "rotation", -90f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InDownRight(view: View): AnimatorSet {

        val x = (view.width - view.paddingRight).toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "rotation", 90f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InUpLeft(view: View): AnimatorSet {

        val x = view.paddingLeft.toFloat()
        val y = view.height - view.paddingBottom.toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "rotation", 90f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun InUpRight(view: View): AnimatorSet {

        val x = (view.width - view.paddingRight).toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "rotation", -90f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    /*
    out
     */

    override fun Out(view: View): AnimatorSet {

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", 0f, 200f)

        return AnimatorSet().apply {
            playTogether(object1, object2)
        }

    }

    override fun OutDownLeft(view: View): AnimatorSet {

        val x = view.paddingLeft.toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", 0f, 90f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }


    override fun OutDownRight(view: View): AnimatorSet {

        val x = (view.width - view.paddingRight).toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", 0f, -90f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun OutUpLeft(view: View): AnimatorSet {

        val x = (view.paddingLeft).toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", 0f, -90f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

    override fun OutUpRight(view: View): AnimatorSet {

        val x = (view.width - view.paddingRight).toFloat()
        val y = (view.height - view.paddingBottom).toFloat()

        val object1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val object2 = ObjectAnimator.ofFloat(view, "rotation", 0f, 90f)
        val object3 = ObjectAnimator.ofFloat(view, "pivotX", x, x)
        val object4 = ObjectAnimator.ofFloat(view, "pivotY", y, y)

        return AnimatorSet().apply {
            playTogether(object1, object2, object3, object4)
        }

    }

}