package com.frogobox.ads.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * Created by faisalamir on 22/03/22
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

@Keep
data class FrogoUnityId(

    @SerializedName("unityGameID")
    var unityGameID: String = "",

    @SerializedName("unityInterstitialID")
    var unityInterstitialID: List<String> = listOf("", "", ""),

    @SerializedName("unityBannerId")
    var unityBannerId: List<String> = listOf("", "", ""),

    @SerializedName("unityRewardedID")
    var unityRewardedID: List<String> = listOf("", "", "")

) {
    fun getUnityBannerId(index: Int = 0): String = unityBannerId.getOrNull(index).orEmpty()
    fun getUnityInterstitialId(index: Int = 0): String = unityInterstitialID.getOrNull(index).orEmpty()
    fun getUnityRewardedId(index: Int = 0): String = unityRewardedID.getOrNull(index).orEmpty()
}
