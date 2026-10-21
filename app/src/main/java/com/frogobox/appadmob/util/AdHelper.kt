package com.frogobox.appadmob.util

import android.content.Context
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


object AdHelper {

    fun getAdOpenAppUnitId(context: Context?): String {
        return if (context != null) {
            val pref = PreferenceDelegatesImpl(context, Constant.PREFERENCE_NAME)
            val current = pref.getPrefString(Constant.PREF_AD_UNIT_ID, Constant.AD_UNIT_ID)
            if (current.contains("3419835294") || current.isBlank()) {
                pref.savePrefString(Constant.PREF_AD_UNIT_ID, Constant.AD_UNIT_ID)
                Constant.AD_UNIT_ID
            } else {
                current
            }
        } else {
            Constant.AD_UNIT_ID
        }
    }

}