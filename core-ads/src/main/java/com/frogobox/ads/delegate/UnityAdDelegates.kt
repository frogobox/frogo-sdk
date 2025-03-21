package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
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


interface UnityAdDelegates {

    fun setupUnityAdDelegates(activity: AppCompatActivity)

    fun setupUnityAdApp(
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback? = null
    )

    fun showUnityAdInterstitial(
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback? = null
    )

}