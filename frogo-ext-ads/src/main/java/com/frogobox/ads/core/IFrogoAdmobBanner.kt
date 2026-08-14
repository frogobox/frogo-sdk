package com.frogobox.ads.core

import android.content.Context
import android.widget.RelativeLayout
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

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
 * GitHub   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.admob
 *
 */


interface IFrogoAdmobBanner {

    fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobBannerCallback? = null
    )

    // ---------------------------------------------------------------------------------------------

    fun showAdBannerContainer(
        context: Context,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobBannerCallback? = null
    )

}
