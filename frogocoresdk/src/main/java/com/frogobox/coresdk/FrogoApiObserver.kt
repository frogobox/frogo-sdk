package com.frogobox.coresdk


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

    abstract fun onSuccess(data: M)

    abstract fun onFailure(code: Int, errorMessage: String)

    // ---------------------------------------------------------------------------------------------

    override fun onComplete() {
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> onFailure(504, "Error response with ${e.message}")
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

    }
}