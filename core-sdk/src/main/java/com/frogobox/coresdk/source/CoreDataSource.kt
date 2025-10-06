package com.frogobox.coresdk.source

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


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

abstract class CoreDataSource {

    companion object {
        val TAG: String = CoreDataSource::class.java.simpleName
    }

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun onClearDisposables() {
        compositeDisposable.clear()
    }

    fun addSubscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}