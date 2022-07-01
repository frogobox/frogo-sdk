package com.frogobox.sdk.view

import androidx.lifecycle.ViewModel
import com.frogobox.sdk.ext.showLogD
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
abstract class FrogoViewModel2 : ViewModel() {

    companion object {
        val TAG: String = FrogoViewModel2::class.java.simpleName
    }

    var eventFailed = FrogoMutableLiveData<String>()
    var eventSuccess = FrogoMutableLiveData<String>()

    var eventEmptyState = FrogoMutableLiveData<Boolean>()
    var eventFailedState = FrogoMutableLiveData<Boolean>()
    var eventFinishState = FrogoMutableLiveData<Boolean>()
    var eventSuccessState = FrogoMutableLiveData<Boolean>()
    var eventNoInternetState = FrogoMutableLiveData<Boolean>()
    var eventShowProgressState = FrogoMutableLiveData<Boolean>()

    open fun onStart() {
        showLogD<FrogoViewModel2>("onStart()")
    }

    open fun onClearDisposable() {
        showLogD<FrogoViewModel2>("onClearDisposable()")
    }

}