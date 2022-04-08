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

    abstract fun onFinish()

    abstract fun onFailure(code: Int, errorMessage: String)

    abstract fun onSuccess(data: M)

    abstract fun onStartObserver(disposable: Disposable)

    // ---------------------------------------------------------------------------------------------

    override fun onComplete() {
        onFinish()
    }

    override fun onSubscribe(d: Disposable) {
        onStartObserver(d)
    }

    override fun onNext(t: M) {
        onSuccess(t)
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
                onFailure(code, msg)
            }
            is UnknownHostException -> onFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            is SocketTimeoutException -> onFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            else -> onFailure(-1, e.message ?: "Unknown error occured")
        }

        onFinish()
    }
}