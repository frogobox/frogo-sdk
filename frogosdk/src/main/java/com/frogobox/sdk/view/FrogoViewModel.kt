package com.frogobox.sdk.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.log.FLog
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.util.FrogoMutableLiveData

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
@Deprecated("Use FrogoViewModel2 instead")
abstract class FrogoViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG: String = FrogoViewModel::class.java.simpleName
    }

    protected var _eventSuccess = MutableLiveData<String>()
    var eventSuccess: LiveData<String> = _eventSuccess

    protected var _eventEmptyState = MutableLiveData<Boolean>()
    var eventEmptyState: LiveData<Boolean> = _eventEmptyState

    protected var _eventFinishState = MutableLiveData<Boolean>()
    var eventFinishState: LiveData<Boolean> = _eventFinishState

    protected var _eventSuccessState = MutableLiveData<Boolean>()
    var eventSuccessState: LiveData<Boolean> = _eventSuccessState

    protected var _eventNoInternetState = MutableLiveData<Boolean>()
    var eventNoInternetState: LiveData<Boolean> = _eventNoInternetState

    protected var _eventShowProgressState = MutableLiveData<Boolean>()
    var eventShowProgressState: LiveData<Boolean> = _eventShowProgressState

    var eventFailed = FrogoMutableLiveData<String>()
    var eventEmpty = FrogoMutableLiveData<Boolean>().apply { postValue(false) }
    var eventNoInternet = FrogoMutableLiveData<Boolean>().apply { postValue(false) }
    var eventFinish = FrogoMutableLiveData<Boolean>().apply { postValue(false) }
    var eventFailedState = FrogoMutableLiveData<Boolean>().apply { postValue(false) }
    var eventShowProgress = FrogoMutableLiveData<Boolean>().apply { postValue(false) }

    protected fun showLogDebug(message: String) {
        FLog.d("$TAG : $message")
    }

    protected fun showLogError(message: String) {
        FLog.e("$TAG : $message")
    }

    open fun onStart() {
        showLogD<FrogoViewModel>("onStart()")
    }

    open fun onClearDisposable() {
        showLogD<FrogoViewModel>("onClearDisposable()")
    }

}