package com.frogobox.sdk.ext

import androidx.lifecycle.MutableLiveData
import com.frogobox.coresdk.observer.FrogoApiObserver
import com.frogobox.coresdk.observer.FrogoLocalObserver
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.FrogoResult
import com.frogobox.coresdk.source.FrogoResultState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
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
            callback.onShowProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : FrogoApiObserver<T>() {
            override fun onApiSuccess(data: T) {
                callback.onHideProgress()
                callback.onSuccess(data)
            }

            override fun onApiFailure(code: Int, errorMessage: String) {
                callback.onHideProgress()
                callback.onFailed(code, errorMessage)
            }

            override fun onApiFinish() {
                callback.onHideProgress()
                callback.onFinish()
            }

            override fun onApiStartObserver(disposable: Disposable) {
                addCallbackSubscribe(disposable)
            }
        })
}

fun <T : Any> Observable<T>.doApiRequestResult(
    result: MutableLiveData<FrogoResult<T>>,
    addCallbackSubscribe: (d: Disposable) -> Unit
) {
    doApiRequest(object : FrogoDataResponse<T> {
        override fun onShowProgress() {
            result.value = FrogoResult.Loading(true)
        }

        override fun onHideProgress() {
            result.value = FrogoResult.Loading(false)
        }

        override fun onSuccess(data: T) {
            result.value = FrogoResult.Success(data)
        }

        override fun onFailed(statusCode: Int, errorMessage: String) {
            result.value = FrogoResult.Error(statusCode, errorMessage)
        }

        override fun onFinish() {
            result.value = FrogoResult.Finish()
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
            callback.onShowProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : FrogoLocalObserver<T>() {

            override fun onLocalFinish() {
                callback.onHideProgress()
                callback.onFinish()
            }

            override fun onLocalFailure(code: Int, errorMessage: String) {
                callback.onHideProgress()
                callback.onFailed(code, errorMessage)
            }

            override fun onLocalSuccess(data: T) {
                callback.onHideProgress()
                callback.onSuccess(data)
            }

            override fun onLocalStartObserver(disposable: Disposable) {
                callback.onHideProgress()
                addCallbackSubscribe(disposable)
            }

        })
}


fun <T : Any> Single<T>.fetchRoomDBResult(
    result: MutableLiveData<FrogoResult<T>>,
    addCallbackSubscribe: (d: Disposable) -> Unit
) {
    fetchRoomDB(object : FrogoDataResponse<T> {
        override fun onShowProgress() {
            result.value = FrogoResult.Loading(true)
        }

        override fun onHideProgress() {
            result.value = FrogoResult.Loading(false)
        }

        override fun onSuccess(data: T) {
            result.value = FrogoResult.Success(data)
        }

        override fun onFailed(statusCode: Int, errorMessage: String) {
            result.value = FrogoResult.Error(statusCode, errorMessage)
        }

        override fun onFinish() {
            result.value = FrogoResult.Finish()
        }
    }) {
        addCallbackSubscribe(it)
    }
}

fun <T : Any> Observable<T>.fetchPreference(callback: FrogoDataResponse<T>) {
    subscribeOn(Schedulers.io())
        .doOnSubscribe {
            callback.onShowProgress()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess(it)
            callback.onHideProgress()
            callback.onFinish()
        }, {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onHideProgress()
            callback.onFinish()
        })
}

// -------------------------------------------------------------------------------------------------

fun Completable.executeRoomDB(callback: FrogoStateResponse) {
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess()
            callback.onHideProgress()
            callback.onFinish()
        }) {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onHideProgress()
            callback.onFinish()
        }
}

fun Completable.executeRoomDBResult(result: MutableLiveData<FrogoResultState>) {
    executeRoomDB(object : FrogoStateResponse {
        override fun onSuccess() {
            result.value = FrogoResultState.Success()
        }

        override fun onFailed(statusCode: Int, errorMessage: String) {
            result.value = FrogoResultState.Error(statusCode, errorMessage)
        }

        override fun onFinish() {
            result.value = FrogoResultState.Finish()
        }

        override fun onHideProgress() {
            result.value = FrogoResultState.Loading(false)
        }

        override fun onShowProgress() {
            result.value = FrogoResultState.Loading(true)
        }
    })
    
}

fun Completable.executePreference(callback: FrogoStateResponse) {
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess()
            callback.onHideProgress()
            callback.onFinish()
        }) {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onHideProgress()
            callback.onFinish()
        }
}

fun Completable.executeAction(callback: FrogoStateResponse) {
    callback.onShowProgress()
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            callback.onSuccess()
            callback.onHideProgress()
            callback.onFinish()
        }) {
            it.message?.let { it1 -> callback.onFailed(200, it1) }
            callback.onHideProgress()
            callback.onFinish()
        }
}

// -------------------------------------------------------------------------------------------------