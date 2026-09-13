package com.frogobox.sdk.piracychecker.activities

import android.content.Context
import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat

internal fun Context.getAppName(): String {
    var name: String = try {
        (packageManager?.getApplicationLabel(applicationInfo) ?: "").toString()
    } catch (e: Exception) {
        ""
    }
    if (name.isNotBlank() && name.isNotEmpty()) return name
    
    val stringRes = applicationInfo?.labelRes ?: 0
    name = if (stringRes == 0) {
        applicationInfo?.nonLocalizedLabel?.toString() ?: ""
    } else {
        try {
            getString(stringRes)
        } catch (e: Exception) {
            ""
        }
    }
    return name
}

internal fun Window.setupLightStatusBar(enable: Boolean) {
    WindowInsetsControllerCompat(this, decorView).isAppearanceLightStatusBars = enable
}