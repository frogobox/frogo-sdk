package com.frogobox.ads.core

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 10/02/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.admob
 *
 */


interface IFrogoAdmobInterstitial {

    fun showAdInterstitial(
        activity: AppCompatActivity,
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobInterstitialCallback? = null
    )

}
