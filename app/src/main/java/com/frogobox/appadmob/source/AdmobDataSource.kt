package com.frogobox.appadmob.source

import android.content.Context
import com.frogobox.coresdk.response.FrogoDataResponse
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd


/**
 * Created by faisalamir on 19/04/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface AdmobDataSource {

    fun getInterstitial(context: Context, callback: FrogoDataResponse<InterstitialAd>)

    fun saveInterstitial(interstitialAd: InterstitialAd)

}