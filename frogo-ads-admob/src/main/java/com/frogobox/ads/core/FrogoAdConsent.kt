package com.frogobox.ads.core

import android.content.Context
import android.telephony.TelephonyManager
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.FormError
import com.google.android.ump.UserMessagingPlatform


/**
 * Created by faisalamir on 26/03/22
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


object FrogoAdConsent {

    fun getCountryCode(context: Context): String {
        val tm = runCatching { context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager }.getOrNull()
        val networkCountry = tm?.networkCountryIso?.uppercase().orEmpty()
        if (networkCountry.isNotBlank()) return networkCountry
        val simCountry = tm?.simCountryIso?.uppercase().orEmpty()
        if (simCountry.isNotBlank()) return simCountry
        val localeCountry = runCatching {
            val config = context.resources?.configuration
            if (config != null) {
                val locales = androidx.core.os.ConfigurationCompat.getLocales(config)
                if (!locales.isEmpty) {
                    locales[0]?.country?.uppercase().orEmpty()
                } else ""
            } else ""
        }.getOrDefault("")
        return localeCountry.ifBlank { java.util.Locale.getDefault().country.uppercase() }
    }

    fun listEEACountry(): List<String> {
        return listOf(
            "AT", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "DE", "GR", "HU", "IE",
            "IT", "LV", "LT", "LU", "MT", "NL", "PL", "PT", "RO", "SK", "SI", "ES", "SE"
        )
    }

    fun listUKCountry(): List<String> {
        return listOf("GB", "GG", "IM", "JE")
    }

    fun listAdConsentCountry(): List<String> {
        return listEEACountry() + listUKCountry()
    }

    fun isAdConsentCountry(context: Context): Boolean {
        return listAdConsentCountry().contains(getCountryCode(context))
    }

    /**
     * Helper method to call UMP SDK to request consent information and load/show consent form if required.
     * Aligns with official GoogleMobileAdsConsentManager from GMA Next-Gen SDK examples.
     */
    fun gatherConsent(
        activity: android.app.Activity,
        testDeviceHashedId: String? = null,
        tagForUnderAgeOfConsent: Boolean = false,
        onConsentGatheringComplete: (FormError?) -> Unit
    ) {
        if (activity.isFinishing || activity.isDestroyed) {
            onConsentGatheringComplete(null)
            return
        }

        val consentInformation: ConsentInformation =
            UserMessagingPlatform.getConsentInformation(activity)

        val paramsBuilder = ConsentRequestParameters.Builder()
            .setTagForUnderAgeOfConsent(tagForUnderAgeOfConsent)

        if (!testDeviceHashedId.isNullOrBlank()) {
            val debugSettings = ConsentDebugSettings.Builder(activity)
                .addTestDeviceHashedId(testDeviceHashedId)
                .build()
            paramsBuilder.setConsentDebugSettings(debugSettings)
        }

        consentInformation.requestConsentInfoUpdate(
            activity,
            paramsBuilder.build(),
            {
                if (activity.isFinishing || activity.isDestroyed) {
                    onConsentGatheringComplete(null)
                    return@requestConsentInfoUpdate
                }
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
                    onConsentGatheringComplete(formError)
                }
            },
            { requestConsentError ->
                onConsentGatheringComplete(requestConsentError)
            }
        )
    }

    /** Helper variable to determine if the app can request ads based on obtained consent. */
    fun canRequestAds(context: Context): Boolean {
        return UserMessagingPlatform.getConsentInformation(context).canRequestAds()
    }

    /** Helper variable to determine if the GDPR / privacy options form is required. */
    fun isPrivacyOptionsRequired(context: Context): Boolean {
        return UserMessagingPlatform.getConsentInformation(context).privacyOptionsRequirementStatus ==
            ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
    }

    /**
     * Helper method to call UMP SDK to show the privacy options form on demand.
     */
    fun showPrivacyOptionsForm(
        activity: android.app.Activity,
        onDismissListener: (FormError?) -> Unit
    ) {
        if (activity.isFinishing || activity.isDestroyed) {
            onDismissListener(null)
            return
        }
        UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
            onDismissListener(formError)
        }
    }

    fun showConsent(callback: IFrogoAdConsent) {
        if (isAdConsentCountry(callback.activity())) {
            setupConsent(callback)
        } else {
            callback.onNotUsingAdConsent()
        }
    }

    private fun setupConsent(callback: IFrogoAdConsent) {
        val act = callback.activity()
        if (act.isFinishing || act.isDestroyed) {
            return
        }

        val consentInformation: ConsentInformation =
            UserMessagingPlatform.getConsentInformation(act)

        val params = if (callback.isDebug()) {
            val debugSettings = ConsentDebugSettings.Builder(act)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .addTestDeviceHashedId("TEST-DEVICE-HASHED-ID")
                .build()

            ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(callback.isUnderAgeAd())
                .setConsentDebugSettings(debugSettings)
                .build()
        } else {
            ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(callback.isUnderAgeAd())
                .build()
        }

        consentInformation.requestConsentInfoUpdate(
            act,
            params,
            {
                if (act.isFinishing || act.isDestroyed) {
                    return@requestConsentInfoUpdate
                }
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(act) { formError ->
                    if (formError != null) {
                        callback.onConsentError(formError)
                    } else if (consentInformation.canRequestAds()) {
                        callback.onConsentSuccess()
                    } else {
                        callback.onNotUsingAdConsent()
                    }
                }
            },
            { formError ->
                callback.onConsentError(formError)
            }
        )
    }

}