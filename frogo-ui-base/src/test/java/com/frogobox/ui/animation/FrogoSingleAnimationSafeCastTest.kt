package com.frogobox.ui.animation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FrogoSingleAnimationSafeCastTest {

    @Test
    fun testNonActivityContextDoesNotCrash() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()

        // Must execute safely without throwing ClassCastException
        FrogoSingleAnimation.slideLeft(appContext)
        FrogoSingleAnimation.slideRight(appContext)
        FrogoSingleAnimation.slideDown(appContext)
        FrogoSingleAnimation.slideUp(appContext)
        FrogoSingleAnimation.zoom(appContext)
        FrogoSingleAnimation.fade(appContext)
        FrogoSingleAnimation.windmill(appContext)
        FrogoSingleAnimation.spin(appContext)
        FrogoSingleAnimation.diagonal(appContext)
        FrogoSingleAnimation.split(appContext)
        FrogoSingleAnimation.shrink(appContext)
        FrogoSingleAnimation.card(appContext)
        FrogoSingleAnimation.inAndOut(appContext)
        FrogoSingleAnimation.swipeLeft(appContext)
        FrogoSingleAnimation.swipeRight(appContext)
    }
}
