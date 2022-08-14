package com.frogobox.sdk.ext

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.*
import com.frogobox.sdk.widget.webview.WebViewCallback


/*
 * Created by faisalamir on 19/07/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadUrlExt(url: String, auth: HashMap<String, String>, callback: WebViewCallback) {

    showLogDebug("WebViewExt : url : $url")
    showLogDebug("WebViewExt : auth : $auth")

    callback.onShowProgress()
    CookieManager.getInstance().setAcceptCookie(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
    }

    apply {

        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.mediaPlaybackRequiresUserGesture = false
        settings.loadsImagesAutomatically = true
        settings.loadWithOverviewMode = true
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        webChromeClient = WebChromeClient()

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showLogDebug("WebViewExt : onPageFinished : $url")
                callback.onHideProgress()
                callback.onFinish()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (error != null) {
                        showLogDebug("WebViewExt : onReceivedError : ${error.errorCode}")
                        showLogDebug("WebViewExt : onReceivedError : ${error.description}")
                    }
                }
                callback.onHideProgress()
                callback.onFailed()
            }
        }

    }.loadUrl(url, auth)

}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadUrlExt(url: String, callback: WebViewCallback) {

    showLogDebug("WebViewExt : url : $url")

    callback.onShowProgress()
    CookieManager.getInstance().setAcceptCookie(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
    }

    apply {

        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.mediaPlaybackRequiresUserGesture = false
        settings.loadsImagesAutomatically = true
        settings.loadWithOverviewMode = true
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        webChromeClient = WebChromeClient()

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showLogDebug("WebViewExt : onPageFinished : $url")
                callback.onHideProgress()
                callback.onFinish()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (error != null) {
                        showLogDebug("WebViewExt : onReceivedError : ${error.errorCode}")
                        showLogDebug("WebViewExt : onReceivedError : ${error.description}")
                    }
                }
                callback.onHideProgress()
                callback.onFailed()
            }
        }

    }.loadUrl(url)

}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadUrlExt(url: String) {

    showLogDebug("WebViewExt : url : $url")

    CookieManager.getInstance().setAcceptCookie(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
    }

    apply {
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.mediaPlaybackRequiresUserGesture = false
        settings.loadsImagesAutomatically = true
        settings.loadWithOverviewMode = true
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        webChromeClient = WebChromeClient()

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showLogDebug("WebViewExt : onPageFinished : $url")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (error != null) {
                        showLogDebug("WebViewExt : onReceivedError : ${error.errorCode}")
                        showLogDebug("WebViewExt : onReceivedError : ${error.description}")
                    }
                }
            }
        }

    }.loadUrl(url)

}