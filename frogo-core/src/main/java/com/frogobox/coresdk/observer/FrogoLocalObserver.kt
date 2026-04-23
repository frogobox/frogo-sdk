package com.frogobox.coresdk.observer

import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
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
abstract class FrogoLocalObserver<M : Any> : SingleObserver<M> {

    companion object {
        val TAG: String = FrogoLocalObserver::class.java.simpleName
    }

    abstract fun onLocalFinish()
    abstract fun onLocalFailure(code: Int, errorMessage: String)
    abstract fun onLocalSuccess(data: M)
    abstract fun onLocalStartObserver(disposable: Disposable)

    override fun onSuccess(t: M) {
        onLocalSuccess(t)
        onLocalFinish()
    }

    override fun onSubscribe(d: Disposable) {
        onLocalStartObserver(d)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()

                when (code) {
                    504 -> {
                        msg = "${e.message()} : Error Response"
                    }
                    502, 404 -> {
                        msg = "${e.message()} : Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = "${e.message()} : Bad Request"
                    }
                    401 -> {
                        msg = "${e.message()} : Not Authorized"
                    }
                }

                onLocalFailure(code, msg)
            }

            is UnknownHostException -> onLocalFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            is SocketTimeoutException -> onLocalFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            else -> onLocalFailure(-1, e.message ?: "Unknown error occured")
        }
        onLocalFinish()
    }
}