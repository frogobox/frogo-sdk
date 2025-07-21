package com.frogobox.recycler.widget

import androidx.viewbinding.ViewBinding
import com.frogobox.recycler.core.FrogoBindingAdapter
import com.frogobox.recycler.core.FrogoSingleRv
import com.frogobox.recycler.core.FrogoSingleRvBinding
import com.frogobox.recycler.core.FrogoViewAdapter
import com.frogobox.recycler.core.IFrogoBuilderRv
import com.frogobox.recycler.core.IFrogoBuilderRvBinding

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * FrogoRecyclerViewAdapter
 * Copyright (C) 31/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.recycler.view
 *
 */

interface IFrogoRecyclerView {

    // Setup linear vertical recycler view
    fun isViewLinearVertical(dividerItem: Boolean = false)

    // Setup linear horizontal recycler view
    fun isViewLinearHorizontal(dividerItem: Boolean = false)

    // Setup staggered grid recycler view
    fun isViewStaggeredGrid(spanCount: Int = 1)

    // Setup grid recycler view
    fun isViewGrid(spanCount: Int = 1)

    // Setup SingletonRclass
    fun <T> injector(): FrogoSingleRv<T>

    // Setup Singleton ViewBinding Class
    fun <T, VB : ViewBinding> injectorBinding(): FrogoSingleRvBinding<T, VB>

    // Setup Builder
    fun <T> builder(listener: IFrogoBuilderRv<T>)

    // Setup Builder Binding
    fun <T, VB : ViewBinding> builderBinding(listener: IFrogoBuilderRvBinding<T, VB>)

    fun <T> getAdapterExt(): FrogoViewAdapter<T>

    fun <T> setupData(data: List<T>)

    fun <T, VB : ViewBinding> getAdapterBindingExt(): FrogoBindingAdapter<T, VB>

    fun <T, VB : ViewBinding> setupDataBinding(data: List<T>)

}