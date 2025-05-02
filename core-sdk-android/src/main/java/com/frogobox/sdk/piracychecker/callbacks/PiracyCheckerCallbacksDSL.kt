package com.frogobox.sdk.piracychecker.callbacks

import com.frogobox.sdk.piracychecker.PiracyChecker

class PiracyCheckerCallbacksDSL internal constructor(private val checker: PiracyChecker) {
    fun allow(allowCallback: AllowCallback): PiracyChecker =
        checker.allowCallback(allowCallback)
    
    fun doNotAllow(doNotAllowCallback: DoNotAllowCallback): PiracyChecker =
        checker.doNotAllowCallback(doNotAllowCallback)
    
    fun onError(onErrorCallback: OnErrorCallback): PiracyChecker =
        checker.onErrorCallback(onErrorCallback)
}