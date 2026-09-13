package com.frogobox

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.os.ConfigurationCompat
import com.frogobox.ads.FrogoAdmobApplication
import com.frogobox.appadmob.util.AdHelper
import com.frogobox.appsdk.util.AppConstant.CHANNEL_ID
import com.frogobox.appsdk.util.AppConstant.CHANNEL_NAME
import com.frogobox.sdk.FrogoApplication
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

/**
 * Created by faisalamir on 19/08/21
 * FrogoNotification
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

@HiltAndroidApp(FrogoAdmobApplication::class)
class FrogoApp : Hilt_FrogoApp() {

    companion object {

        lateinit var instance: FrogoApplication

        fun getContext(): Context = instance.applicationContext

        fun getCurrentLocale(): Locale? {
            return ConfigurationCompat.getLocales(instance.resources.configuration)[0]
        }

    }

    override fun onCreateExt() {
        super.onCreateExt()
        instance = this
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    override fun getAdOpenAppUnitId(context: Context?): String {
        return AdHelper.getAdOpenAppUnitId(context)
    }

}