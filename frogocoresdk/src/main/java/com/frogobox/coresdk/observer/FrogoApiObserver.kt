package com.frogobox.coresdk.observer


import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/*
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class FrogoApiObserver<M> : Observer<M> {

    companion object {
        val TAG: String = FrogoApiObserver::class.java.simpleName
    }

    abstract fun onApiFinish()
    abstract fun onApiFailure(code: Int, errorMessage: String)
    abstract fun onApiSuccess(data: M)
    abstract fun onApiStartObserver(disposable: Disposable)

    // ---------------------------------------------------------------------------------------------

    override fun onComplete() {
        onApiFinish()
    }

    override fun onSubscribe(d: Disposable) {
        onApiStartObserver(d)
    }

    override fun onNext(t: M) {
        onApiSuccess(t)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()

                when (code) {
                    504 -> {
                        msg = "${e.message()} Error Response"
                    }
                    502, 404 -> {
                        msg = "${e.message()} Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = "${e.message()} Bad Request"
                    }
                    401 -> {
                        msg = "${e.message()} Not Authorized"
                    }
                }
                onApiFailure(code, msg)
            }
            is UnknownHostException -> onApiFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            is SocketTimeoutException -> onApiFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            else -> onApiFailure(-1, e.message ?: "Unknown error occured")
        }

        onApiFinish()
    }
}