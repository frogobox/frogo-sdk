package com.frogobox.sdk.ext

import android.annotation.SuppressLint
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.frogobox.sdk.widget.webview.WebViewCallback


/**
 * Created by faisalamir on 19/07/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadUrlFrogoExt(
    url: String,
    auth: Map<String, String>? = null,
    callback: WebViewCallback? = null,
) {
    callback?.onShowProgress()

    if (url.isBlank()) {
        callback?.onHideProgress()
        callback?.onFailed()
    } else {

        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)

        apply {

            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            settings.mediaPlaybackRequiresUserGesture = false
            settings.loadsImagesAutomatically = true
            settings.loadWithOverviewMode = true

            settings.useWideViewPort = true
            settings.allowFileAccess = false
            settings.allowContentAccess = false

            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.javaScriptEnabled = true

            settings.mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW

            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

            webChromeClient = WebChromeClient()

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    callback?.onHideProgress()
                    callback?.onFinish()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?,
                ) {
                    super.onReceivedError(view, request, error)
                    if (request == null || request.isForMainFrame) {
                        callback?.onHideProgress()
                        callback?.onFailed()
                    }
                }
            }

        }

        if (url.contains("</html>")) {
            loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        } else {
            val isValidUrl = url.startsWith("http://") || url.startsWith("https://")
            if (!isValidUrl) {
                callback?.onHideProgress()
                callback?.onFailed()
            } else {
                auth?.let {
                    loadUrl(url, it)
                } ?: run {
                    loadUrl(url)
                }
            }
        }

    }

}