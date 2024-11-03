package com.frogobox.appsdk

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.frogobox.appapi.di.consumeApiModule
import com.frogobox.appapi.di.repositoryApiModule
import com.frogobox.appapi.di.viewModelApiModule
import com.frogobox.appsdk.di.repositoryModule
import com.frogobox.appsdk.di.viewModelModule
import com.frogobox.appsdk.util.AppConstant.CHANNEL_ID
import com.frogobox.appsdk.util.AppConstant.CHANNEL_NAME
import com.frogobox.sdk.FrogoApplication
import java.util.Locale

/**
 * Created by faisalamir on 19/08/21
 * FrogoNotification
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

class FrogoApp : FrogoKoinApplication() {

    companion object {

        lateinit var instance: FrogoApplication

        fun getContext(): Context = instance.applicationContext

        fun getCurrentLocale(): Locale? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                instance.resources.configuration.locales[0]
            } else {
                instance.resources.configuration.locale
            }
        }

    }

    override fun setupKoinModule(koinApplication: org.koin.core.KoinApplication) {
        koinApplication.modules(
            listOf(
                repositoryModule,
                viewModelModule,
                repositoryApiModule,
                viewModelApiModule,
                consumeApiModule
            )
        )
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

}