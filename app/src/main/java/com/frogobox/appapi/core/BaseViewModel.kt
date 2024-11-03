package com.frogobox.appapi.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.sdk.view.FrogoViewModel2


/**
 * Created by faisalamir on 01/05/22
 * FrogoConsumeApi
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

open class BaseViewModel : FrogoViewModel2() {

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

}