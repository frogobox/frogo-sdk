package com.frogobox.appadmob.mvvm.main

import android.content.Context
import com.frogobox.BaseViewModel
import com.frogobox.appadmob.source.AdmobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by faisalamir on 19/04/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

@HiltViewModel
class MainAdmobViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AdmobRepository
) : BaseViewModel(context, repository)