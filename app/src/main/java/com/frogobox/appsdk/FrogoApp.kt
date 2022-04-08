package com.frogobox.appsdk

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.frogobox.appsdk.di.repositoryModule
import com.frogobox.appsdk.di.viewModelModule
import com.frogobox.sdk.FrogoApplication

/*
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

class FrogoApp : FrogoApplication() {

    companion object {
        const val NOTIFICATION_ID = 2
        const val CHANNEL_ID = "CHANNEL_$NOTIFICATION_ID"
        const val CHANNEL_NAME = "CHANNEL_NAME_$CHANNEL_ID"
    }

    override fun setupKoinModule(koinApplication: org.koin.core.KoinApplication) {
        koinApplication.modules(listOf(repositoryModule, viewModelModule))
    }

    override fun setupOnCreate() {
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