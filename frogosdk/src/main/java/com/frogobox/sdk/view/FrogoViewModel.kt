package com.frogobox.sdk.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.frogobox.log.FLog
import com.frogobox.sdk.util.FrogoMutableLiveData

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
abstract class FrogoViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG: String = FrogoViewModel::class.java.simpleName
    }

    var eventShowProgress = FrogoMutableLiveData<Boolean>()
    var eventEmpty = FrogoMutableLiveData<Boolean>()
    var eventNoInternet = FrogoMutableLiveData<Boolean>()
    var eventFinish = FrogoMutableLiveData<Boolean>()
    var eventFailedState = FrogoMutableLiveData<Boolean>()
    var eventFailed = FrogoMutableLiveData<String>()

    protected fun showLogDebug(message: String) {
        FLog.d("$TAG : $message")
    }

    protected fun showLogError(message: String) {
        FLog.e("$TAG : $message")
    }

    open fun start() {}

    open fun onClearDisposable() {}

}