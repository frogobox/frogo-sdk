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

fun <T : Any> Single<T>.doLocalRequest(
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("doLocalRequest : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("doLocalRequest : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : FrogoLocalObserver<T>() {

            override fun onLocalFinish() {
                showLogDebug("doLocalRequest : onLocalFinish / onFinish")
                callback.onFinish()
            }

            override fun onLocalFailure(code: Int, errorMessage: String) {
                showLogError("doLocalRequest : onLocalFailure / onFailed")
                callback.onFailed(code, errorMessage)
            }

            override fun onLocalSuccess(data: T) {
                showLogDebug("doLocalRequest : onLocalSuccess / onSuccess")
                callback.onSuccess(data)
            }

            override fun onLocalStartObserver(disposable: Disposable) {
                showLogDebug("doLocalRequest : onLocalStartObserver / onStartObserver")
                addCallbackSubcribe(disposable)
            }

        })
}

// -------------------------------------------------------------------------------------------------

fun rxJavaCompletableFromAction(callback: FrogoStateResponse, action: () -> Unit) {
    Completable.fromAction { action() }
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("rxJavaCompletableFromAction : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("rxJavaCompletableFromAction : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("rxJavaCompletableFromAction : onLocalSuccess / onSuccess")
            showLogDebug("rxJavaCompletableFromAction : onLocalFinish / onFinish")
            callback.onSuccess()
            callback.onFinish()
        }) {
            showLogError("rxJavaCompletableFromAction : onLocalFailure / onFailed")
            showLogDebug("rxJavaCompletableFromAction : onLocalFinish / onFinish")
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onFinish()
        }
}

// -------------------------------------------------------------------------------------------------

fun <T : Any> rxJavaObservableSingleJust(data: T, callback: FrogoDataResponse<T>) {
    Observable.just(data)
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("rxJavaObservableSingleJust : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doOnTerminate {
            showLogDebug("rxJavaObservableSingleJust : doOnTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("rxJavaObservableSingleJust : onLocalSuccess / onSuccess")
            callback.onSuccess(it)
        }, {
            showLogError("rxJavaObservableSingleJust : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }
        })
}