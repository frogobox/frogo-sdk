package com.frogobox.sdk

import android.app.Activity
import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.frogobox.sdk.ui.FrogoCustomCrashActivity
import org.acra.config.mailSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra


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

    open fun onCreateExt() {}

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

    open fun customErrorActivity(): Class<out Activity> {
        return FrogoCustomCrashActivity::class.java
    }

    open fun setupACRA() {
        initAcra {
            buildConfigClass = BuildConfig::class.java
            reportFormat = StringFormat.JSON
            alsoReportToAndroidFramework = true
            mailSender {
                //required
                mailTo = "faisalamircs.work@gmail.com"
                //defaults to true
                reportAsFile = true
                //defaults to ACRA-report.stacktrace
                reportFileName = "Crash.txt"
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        onCreateExt()
        setupCAOC()
        setupACRA()
    }

}