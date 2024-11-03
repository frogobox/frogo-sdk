package com.frogobox.appapi.mvvm.movies.core

import android.content.Context
import com.frogobox.appapi.core.BaseViewModel
import com.frogobox.appapi.source.ApiRepository
import com.frogobox.appapi.util.isDebug

/**
 * Created by Faisal Amir on 19/03/23
 * https://github.com/amirisback
 */


open class BaseMovieApiViewModel(
    private val context: Context,
    private val repository: ApiRepository,
) : BaseViewModel() {

    protected val movieApi = repository.consumeMovieApi().usingChuckInterceptor(isDebug, context)

}