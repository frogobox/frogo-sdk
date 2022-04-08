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
        override fun onSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onFinish() {
            callback.onFinish()
        }

        override fun onStartObserver(disposable: Disposable) {

        }
    })
}

// -------------------------------------------------------------------------------------------------

// Single Api Request
fun <T : Any> Observable<T>.doApiRequest(callback: FrogoDataResponse<T>) {
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    subscribe(object : FrogoApiObserver<T>() {
        override fun onSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onFinish() {
            callback.onFinish()
        }

        override fun onStartObserver(disposable: Disposable) {

        }
    })
}