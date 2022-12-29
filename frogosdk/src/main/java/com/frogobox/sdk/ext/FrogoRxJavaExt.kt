package com.frogobox.sdk.ext

import androidx.lifecycle.MutableLiveData
import com.frogobox.coresdk.observer.FrogoApiObserver
import com.frogobox.coresdk.observer.FrogoLocalObserver
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.FrogoResult
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
    addCallbackSubscribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("doApiRequest : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doAfterTerminate {
            showLogDebug("doApiRequest : doAfterTerminate / onHideProgress")
            callback.onHideProgress()
            callback.onFinish()
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
                addCallbackSubscribe(disposable)
            }
        })
}

fun <T : Any> Observable<T>.doApiRequest(
    result: MutableLiveData<FrogoResult<T>>,
    addCallbackSubscribe: (d: Disposable) -> Unit
) {
    doApiRequest(object : FrogoDataResponse<T> {
        override fun onShowProgress() {
            result.postValue(FrogoResult.ShowLoading())
        }

        override fun onHideProgress() {
            result.postValue(FrogoResult.HideLoading())
        }

        override fun onSuccess(data: T) {
            result.postValue(FrogoResult.Success(data))
        }

        override fun onFailed(code: Int, errorMessage: String) {
            result.postValue(FrogoResult.Error(code, errorMessage))
        }

        override fun onFinish() {
            result.postValue(FrogoResult.Finish())
        }
    }) {
        addCallbackSubscribe(it)
    }
}

// -------------------------------------------------------------------------------------------------

fun <T : Any> Single<T>.fetchRoomDB(
    callback: FrogoDataResponse<T>,
    addCallbackSubscribe: (d: Disposable) -> Unit
) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("fetchRoomDB : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doAfterTerminate {
            showLogDebug("fetchRoomDB : doAfterTerminate / onHideProgress")
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
                addCallbackSubscribe(disposable)
            }

        })
}

fun <T : Any> Observable<T>.fetchPreference(callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            showLogDebug("fetchPreference : doOnSubscribe / onShowProgress")
            callback.onShowProgress()
        }
        .doAfterTerminate {
            showLogDebug("fetchPreference : doAfterTerminate / onHideProgress")
            callback.onHideProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("fetchPreference : onLocalSuccess / onSuccess")
            callback.onSuccess(it)

            showLogDebug("fetchPreference : onLocalSuccess / onFinish")
            callback.onFinish()
        }, {
            showLogError("fetchPreference : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }

            showLogDebug("fetchPreference : onLocalSuccess / onFinish")
            callback.onFinish()
        })
}

// -------------------------------------------------------------------------------------------------

fun Completable.executeRoomDB(callback: FrogoStateResponse) {
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executeRoomDB : onLocalSuccess / onSuccess")
            callback.onSuccess()

            showLogDebug("executeRoomDB : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogDebug("executeRoomDB : onLocalFinish / onFinish")
            callback.onFinish()
        }) {
            showLogError("executeRoomDB : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }

            showLogError("executeRoomDB : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogError("executeRoomDB : onLocalFinish / onFinish")
            callback.onFinish()
        }
}

fun Completable.executePreference(callback: FrogoStateResponse) {
    showLogDebug("executePreference : doOnSubscribe / onShowProgress")
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executePreference : onLocalSuccess / onSuccess")
            callback.onSuccess()

            showLogDebug("executePreference : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogDebug("executePreference : onLocalFinish / onFinish")
            callback.onFinish()
        }) {
            showLogError("executePreference : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }

            showLogError("executePreference : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogError("executePreference : onLocalFinish / onFinish")
            callback.onFinish()
        }
}

fun Completable.executeAction(callback: FrogoStateResponse) {
    showLogDebug("executeAction : onLocalSuccess / onShowProgress")
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            showLogDebug("executeAction : onLocalSuccess / onSuccess")
            callback.onSuccess()

            showLogDebug("executeAction : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogDebug("executeAction : onLocalFinish / onFinish")
            callback.onFinish()
        }) {
            showLogError("executeAction : onLocalFailure / onFailed")
            it.message?.let { it1 -> callback.onFailed(200, it1) }

            showLogError("executeAction : doAfterTerminate / onHideProgress")
            callback.onHideProgress()

            showLogError("executeAction : onLocalFinish / onFinish")
            callback.onFinish()
        }
}

// -------------------------------------------------------------------------------------------------