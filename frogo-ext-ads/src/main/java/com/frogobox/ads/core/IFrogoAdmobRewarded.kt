package com.frogobox.ads.core

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback

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


interface IFrogoAdmobRewarded {

    fun showAdRewarded(
        activity: AppCompatActivity,
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

    // ---------------------------------------------------------------------------------------------

    fun showAdRewardedInterstitial(
        activity: AppCompatActivity,
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

}
