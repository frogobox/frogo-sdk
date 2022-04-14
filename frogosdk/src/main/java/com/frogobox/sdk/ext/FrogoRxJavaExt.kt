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

fun <T : Any> Observable<T>.doApiRequest(
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("doApiRequest : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("doApiRequest : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : FrogoApiObserver<T>() {
            override fun onApiSuccess(data: T) {
                showLogDebug("doApiRequest : onApiSuccess / onSuccess")
                callback.onSuccess(data)
            }

            override fun onApiFailure(code: Int, errorMessage: String) {
                showLogError("doApiRequest : onApiFailure / onFailed")
                callback.onFailed(code, errorMessage)
            }

            override fun onApiFinish() {
                showLogDebug("doApiRequest : onApiFinish / onFinish")
                callback.onFinish()
            }

            override fun onApiStartObserver(disposable: Disposable) {
                showLogDebug("doApiRequest : onApiStartObserver / onStartObserver")
                addCallbackSubcribe(disposable)
            }
        })
}

// -------------------------------------------------------------------------------------------------

fun <T : Any> Single<T>.fetchRoomDB(
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("fetchRoomDB : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("fetchRoomDB : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : FrogoLocalObserver<T>() {

            override fun onLocalFinish() {
                showLogDebug("fetchRoomDB : onLocalFinish / onFinish")
                callback.onFinish()
            }

            override fun onLocalFailure(code: Int, errorMessage: String) {
                showLogError("fetchRoomDB : onLocalFailure / onFailed")
                callback.onFailed(code, errorMessage)
            }

            override fun onLocalSuccess(data: T) {
                showLogDebug("fetchRoomDB : onLocalSuccess / onSuccess")
                callback.onSuccess(data)
            }

            override fun onLocalStartObserver(disposable: Disposable) {
                showLogDebug("fetchRoomDB : onLocalStartObserver / onStartObserver")
                addCallbackSubcribe(disposable)
            }

        })
}

fun <T : Any> Observable<T>.fetchPreference(callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("fetchPreference : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("fetchPreference : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("fetchPreference : onLocalSuccess / onSuccess")
            callback.onSuccess(it)
        }, {
            showLogError("fetchPreference : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }
        })
}

// -------------------------------------------------------------------------------------------------

fun Completable.executeRoomDB(callback: FrogoStateResponse) {
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executeRoomDB : onLocalSuccess / onSuccess")
            showLogDebug("executeRoomDB : onLocalFinish / onFinish")
            showLogDebug("executeRoomDB : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            callback.onSuccess()
            callback.onFinish()
        }) {
            showLogError("executeRoomDB : onLocalFailure / onFailed")
            showLogError("executeRoomDB : onLocalFinish / onFinish")
            showLogError("executeRoomDB : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onFinish()
        }
}

fun Completable.executePreference(callback: FrogoStateResponse) {
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executePreference : onLocalSuccess / onSuccess")
            showLogDebug("executePreference : onLocalFinish / onFinish")
            showLogDebug("executePreference : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            callback.onSuccess()
            callback.onFinish()
        }) {
            showLogError("executePreference : onLocalFailure / onFailed")
            showLogError("executePreference : onLocalFinish / onFinish")
            showLogError("executePreference : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onFinish()
        }
}

fun Completable.executeAction(callback: FrogoStateResponse) {
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executeAction : onLocalSuccess / onSuccess")
            showLogDebug("executeAction : onLocalFinish / onFinish")
            showLogDebug("executeAction : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            callback.onSuccess()
            callback.onFinish()
        }) {
            showLogError("executeAction : onLocalFailure / onFailed")
            showLogError("executeAction : onLocalFinish / onFinish")
            showLogError("executeAction : doOnTerminate / onHideProgress")
            callback.onHideProgress()
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onFinish()
        }
}

// -------------------------------------------------------------------------------------------------