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
fun <T : Any> Observable<T>.doApiRequest(
    scheduler: Scheduler?,
    callback: FrogoDataResponse<T>,
    addCallbackSubcribe: (d: Disposable) -> Unit
) {
    if (scheduler != null) {
        showPrintLog("doApiRequest : Scheduler is not null")
        subscribeOn(Schedulers.io())
            .doOnSubscribe {
                showPrintLog("doApiRequest : doOnSubscribe")
                callback.onShowProgress()
            }
            .doAfterTerminate {
                showPrintLog("doApiRequest : doAfterTerminate")
                callback.onHideProgress()
            }
            .observeOn(scheduler)
            .subscribe(object : FrogoApiObserver<T>() {
                override fun onApiSuccess(data: T) {
                    showPrintLog("doApiRequest : onApiSuccess")
                    callback.onSuccess(data)
                }

                override fun onApiFailure(code: Int, errorMessage: String) {
                    showPrintLog("doApiRequest : onApiFailure")
                    callback.onFailed(code, errorMessage)
                }

                override fun onApiFinish() {
                    showPrintLog("doApiRequest : onApiFinish")
                    callback.onFinish()
                }

                override fun onApiStartObserver(disposable: Disposable) {
                    showPrintLog("doApiRequest : onApiStartObserver")
                    addCallbackSubcribe(disposable)
                }

            })
    } else {
        doOnSubscribe {
            showPrintLog("doApiRequest : doOnSubscribe")
            callback.onShowProgress()
        }
            .doAfterTerminate {
                showPrintLog("doApiRequest : doAfterTerminate")
                callback.onHideProgress()
            }
            .subscribe(object : FrogoApiObserver<T>() {
                override fun onApiSuccess(data: T) {
                    showPrintLog("doApiRequest : onApiSuccess")
                    callback.onSuccess(data)
                }

                override fun onApiFailure(code: Int, errorMessage: String) {
                    showPrintLog("doApiRequest : onApiFailure")
                    callback.onFailed(code, errorMessage)
                }

                override fun onApiFinish() {
                    showPrintLog("doApiRequest : onApiFinish")
                    callback.onFinish()
                }

                override fun onApiStartObserver(disposable: Disposable) {
                    showPrintLog("doApiRequest : onApiStartObserver")
                    addCallbackSubcribe(disposable)
                }

            })
    }

}

// -------------------------------------------------------------------------------------------------