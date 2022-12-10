package com.frogobox.sdk.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frogobox.sdk.ext.showLogD

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
abstract class FrogoViewModel2 : ViewModel() {

    companion object {
        val TAG: String = FrogoViewModel2::class.java.simpleName
    }

    protected var _eventFailed = MutableLiveData<String>()
    var eventFailed: LiveData<String> = _eventFailed

    protected var _eventSuccess = MutableLiveData<String>()
    var eventSuccess: LiveData<String> = _eventSuccess

    protected var _eventEmptyState = MutableLiveData<Boolean>()
    var eventEmptyState: LiveData<Boolean> = _eventEmptyState

    protected var _eventFailedState = MutableLiveData<Boolean>()
    var eventFailedState: LiveData<Boolean> = _eventFailedState

    protected var _eventFinishState = MutableLiveData<Boolean>()
    var eventFinishState: LiveData<Boolean> = _eventFinishState

    protected var _eventSuccessState = MutableLiveData<Boolean>()
    var eventSuccessState: LiveData<Boolean> = _eventSuccessState

    protected var _eventNoInternetState = MutableLiveData<Boolean>()
    var eventNoInternetState: LiveData<Boolean> = _eventNoInternetState

    protected var _eventShowProgressState = MutableLiveData<Boolean>()
    var eventShowProgressState: LiveData<Boolean> = _eventShowProgressState

    open fun onStart() {
        showLogD<FrogoViewModel2>("onStart()")
    }

    open fun onClearDisposable() {
        showLogD<FrogoViewModel2>("onClearDisposable()")
    }

}