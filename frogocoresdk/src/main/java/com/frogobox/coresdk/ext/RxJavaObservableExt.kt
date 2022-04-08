package com.frogobox.coresdk.ext

import com.frogobox.coresdk.observer.FrogoApiObserver
import com.frogobox.coresdk.response.FrogoDataResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/*
 * Created by faisalamir on 07/04/22
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

// Single Api Request with scheduler
fun <T : Any> Observable<T>.doApiRequest(scheduler: Scheduler, callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(scheduler)
    subscribe(object : FrogoApiObserver<T>() {
        override fun onApiSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onApiFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onApiFinish() {
            callback.onFinish()
        }

        override fun onApiStartObserver(disposable: Disposable) {

        }
    })
}

fun <T : Any> Observable<T>.doApiRequest(
    scheduler: Scheduler,
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(scheduler)
    subscribe(object : FrogoApiObserver<T>() {
        override fun onApiSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onApiFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onApiFinish() {
            callback.onFinish()
        }

        override fun onApiStartObserver(disposable: Disposable) {
            addCallbackSubcribe(disposable)
        }
    })
}

// -------------------------------------------------------------------------------------------------

// Single Api Request
fun <T : Any> Observable<T>.doApiRequest(callback: FrogoDataResponse<T>) {
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    subscribe(object : FrogoApiObserver<T>() {
        override fun onApiSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onApiFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onApiFinish() {
            callback.onFinish()
        }

        override fun onApiStartObserver(disposable: Disposable) {

        }
    })
}

fun <T : Any> Observable<T>.doApiRequest(
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    subscribe(object : FrogoApiObserver<T>() {
        override fun onApiSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onApiFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onApiFinish() {
            callback.onFinish()
        }

        override fun onApiStartObserver(disposable: Disposable) {
            addCallbackSubcribe(disposable)
        }
    })
}