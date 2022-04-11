package com.frogobox.sdk.ext

import com.frogobox.coresdk.observer.FrogoApiObserver
import com.frogobox.coresdk.observer.FrogoLocalObserver
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/*
 * Created by faisalamir on 08/04/22
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

fun <T : Any> Observable<T>.doApiRequest(callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(AndroidSchedulers.mainThread())
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
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(AndroidSchedulers.mainThread())
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

fun <T : Any> Single<T>.doLocalRequest(callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(AndroidSchedulers.mainThread())
    subscribe(object : FrogoLocalObserver<T>() {

        override fun onLocalFinish() {
            callback.onFinish()
        }

        override fun onLocalFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onLocalSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onLocalStartObserver(disposable: Disposable) {

        }

    })
}

fun <T : Any> Single<T>.doLocalRequest(
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
    doOnSubscribe { callback.onShowProgress() }
    doOnTerminate { callback.onHideProgress() }
    observeOn(AndroidSchedulers.mainThread())
    subscribe(object : FrogoLocalObserver<T>() {

        override fun onLocalFinish() {
            callback.onFinish()
        }

        override fun onLocalFailure(code: Int, errorMessage: String) {
            callback.onFailed(code, errorMessage)
        }

        override fun onLocalSuccess(data: T) {
            callback.onSuccess(data)
        }

        override fun onLocalStartObserver(disposable: Disposable) {
            addCallbackSubcribe(disposable)
        }

    })
}

// -------------------------------------------------------------------------------------------------

fun rxJavaCompletableFromAction(callback: FrogoStateResponse, action: () -> Unit) {
    Completable.fromAction { action() }
        .doOnSubscribe { callback.onShowProgress() }
        .doOnTerminate { callback.onHideProgress() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess()
            callback.onFinish()
        }) {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onFinish()
        }
}

// -------------------------------------------------------------------------------------------------

fun <T : Any> rxJavaObservableSingleJust(data: T, callback: FrogoDataResponse<T>) {
    Observable.just(data)
        .doOnSubscribe { callback.onShowProgress() }
        .doOnTerminate { callback.onHideProgress() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess(it)
        }, {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
        })
}