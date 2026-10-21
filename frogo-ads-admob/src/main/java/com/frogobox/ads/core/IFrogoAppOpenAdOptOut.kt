package com.frogobox.ads.core

/**
 * Marker interface to opt-out an Activity from automatically showing App Open Ads
 * when returning to foreground.
 */
interface IFrogoAppOpenAdOptOut {
    fun isAppOpenAdOptedOut(): Boolean = true
}
