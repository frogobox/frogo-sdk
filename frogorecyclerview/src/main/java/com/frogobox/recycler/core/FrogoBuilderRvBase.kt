package com.frogobox.recycler.core

import androidx.recyclerview.widget.RecyclerView
import com.frogobox.recycler.R


/**
 * Created by faisalamir on 24/07/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */
abstract class FrogoBuilderRvBase<T> {

    protected lateinit var frogoRecyclerView: RecyclerView

    protected var emptyViewId: Int = R.layout.frogo_rv_container_empty_view

    protected var optionAdapter = ""

    protected val listDataFH = mutableListOf<FrogoHolder<T>>()
    protected val listData = mutableListOf<T>()

    protected fun init(frogoRecyclerView: RecyclerView) {
        this.frogoRecyclerView = frogoRecyclerView
    }

}