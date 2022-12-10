package com.frogobox.sdk

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ui.FrogoCustomCrashActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*


/**
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

abstract class FrogoApplication : Application() {

    companion object {
        val TAG: String = FrogoApplication::class.java.simpleName

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

    abstract fun setupKoinModule(koinApplication: KoinApplication)

    @Deprecated("Use onCreateExt() instead", ReplaceWith("onCreateExt()"))
    open fun setupOnCreate() {
        showLogD<FrogoApplication>("setupOnCreate()")
    }

    open fun onCreateExt() {
        showLogD<FrogoApplication>("onCreateExt()")
    }

    open fun customErrorActivity(): Class<out Activity> {
        return FrogoCustomCrashActivity::class.java
    }

    open fun isDebugMode(): Boolean {
        return true
    }

    open fun setupCAOC() {
        if (isDebugMode()) {
            CaocConfig.Builder.create()
                .enabled(true) // default: true
                .showErrorDetails(true) // default: true
                .showRestartButton(true) // default: true
                .logErrorOnRestart(true) // default: true
                .trackActivities(true) // default: false
                .minTimeBetweenCrashesMs(3000) //default: 3000
                .errorActivity(customErrorActivity()) //default: null (default error activity)
                .apply()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupOnCreate()
        onCreateExt()
        startKoin {
            androidContext(this@FrogoApplication)
            androidLogger(
                if (isDebugMode()) {
                    Level.DEBUG
                } else {
                    Level.ERROR
                }
            )
            setupKoinModule(this)
        }
        setupCAOC()
    }

}