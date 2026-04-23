package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback

/**
 * Created by faisalamir on 22/03/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.
 * All rights reserved
 *
 */


interface IFrogoUnityAd {

    fun setupUnityAdApp(
        context: Context,
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback? = null
    )

    // ---------------------------------------------------------------------------------------------

    fun showAdInterstitial(
        activity: Activity,
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback? = null
    )

}