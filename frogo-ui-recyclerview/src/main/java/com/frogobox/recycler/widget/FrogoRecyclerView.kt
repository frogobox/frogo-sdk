package com.frogobox.recycler.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import com.frogobox.recycler.core.FrogoBindingAdapter
import com.frogobox.recycler.core.FrogoBuilderRv
import com.frogobox.recycler.core.FrogoBuilderRvBinding
import com.frogobox.recycler.core.FrogoRvConstant
import com.frogobox.recycler.core.FrogoSingleRv
import com.frogobox.recycler.core.FrogoSingleRvBinding
import com.frogobox.recycler.core.FrogoViewAdapter
import com.frogobox.recycler.core.IFrogoBuilderRv
import com.frogobox.recycler.core.IFrogoBuilderRvBinding


/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * frogo-recycler-view-adapter
 * Copyright (C) 30/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoviewadapter.view
 *
 */
class FrogoRecyclerView : RecyclerView,
    IFrogoRecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @Deprecated(FrogoRvConstant.Deprecated.isViewLinearVertical)
    override fun isViewLinearVertical(dividerItem: Boolean) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if (dividerItem) {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    @Deprecated(FrogoRvConstant.Deprecated.isViewLinearHorizontal)
    override fun isViewLinearHorizontal(dividerItem: Boolean) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        if (dividerItem) {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    @Deprecated(FrogoRvConstant.Deprecated.isViewStaggeredGrid)
    override fun isViewStaggeredGrid(spanCount: Int) {
        layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
    }

    @Deprecated(FrogoRvConstant.Deprecated.isViewGrid)
    override fun isViewGrid(spanCount: Int) {
        layoutManager = GridLayoutManager(context, spanCount)
    }

    override fun <T> injector(): FrogoSingleRv<T> =
        FrogoSingleRv<T>().initSingleton(this)

    override fun <T, VB : ViewBinding> injectorBinding(): FrogoSingleRvBinding<T, VB> {
        return FrogoSingleRvBinding<T, VB>().initSingleton(this)
    }

    override fun <T> builder(listener: IFrogoBuilderRv<T>) {
        return FrogoBuilderRv<T>().initBuilder(this).builder(listener)
    }

    override fun <T, VB : ViewBinding> builderBinding(listener: IFrogoBuilderRvBinding<T, VB>) {
        return FrogoBuilderRvBinding<T, VB>().initBuilder(this).builder(listener)
    }

    override fun <T> getAdapterExt(): FrogoViewAdapter<T> {
        return this.adapter as FrogoViewAdapter<T>
    }

    override fun <T> setItem(data: List<T>) {
        this.getAdapterExt<T>().setItem(data)
    }

    override fun <T> getItem(): List<T> {
        return this.getAdapterExt<T>().getItem()
    }

    override fun <T, VB : ViewBinding> getAdapterBindingExt(): FrogoBindingAdapter<T, VB> {
        return this.adapter as FrogoBindingAdapter<T, VB>
    }

    override fun <T, VB : ViewBinding> setItemBinding(data: List<T>) {
        this.getAdapterBindingExt<T, VB>().setItem(data)
    }

    override fun <T, VB : ViewBinding> getItemBinding(): List<T> {
        return this.getAdapterBindingExt<T, VB>().getItem()
    }

}