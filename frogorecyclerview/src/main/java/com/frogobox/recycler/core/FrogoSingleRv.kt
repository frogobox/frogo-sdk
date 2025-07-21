package com.frogobox.recycler.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.recycler.R


/**
 * Created by Faisal Amir
 * =========================================
 * RecyclerViewAdapter
 * Copyright (C) 27/04/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * FrogoBox Inc
 * com.frogobox.recycler
 *
 */

open class FrogoSingleRv<T> : FrogoSingleRvBase<T>(), IFrogoRv<T> {

    protected lateinit var frogoRecycleView: RecyclerView
    protected lateinit var frogoAdapterCallback: IFrogoViewAdapter<T>
    protected lateinit var frogoViewAdapter: FrogoViewAdapter<T>

    protected var emptyViewId: Int = R.layout.frogo_rv_container_empty_view

    protected var optionAdapter = ""
    protected var customViewId: Int = 0

    override fun initSingleton(frogoRecyclerView: RecyclerView): FrogoSingleRv<T> {
        frogoRecycleView = frogoRecyclerView
        frogoViewAdapter = FrogoViewAdapter()
        return this
    }

    override fun createLayoutLinearVertical(dividerItem: Boolean): FrogoSingleRv<T> {
        baseCreateLayoutLinearVertical(dividerItem)
        return this
    }

    override fun createLayoutLinearVertical(
        dividerItem: Boolean,
        reverseLayout: Boolean,
        stackFromEnd: Boolean
    ): FrogoSingleRv<T> {
        baseCreateLayoutLinearVertical(dividerItem, reverseLayout, stackFromEnd)
        return this
    }

    override fun createLayoutLinearHorizontal(dividerItem: Boolean): FrogoSingleRv<T> {
        baseCreateLayoutLinearHorizontal(dividerItem)
        return this
    }

    override fun createLayoutLinearHorizontal(
        dividerItem: Boolean,
        reverseLayout: Boolean,
        stackFromEnd: Boolean
    ): FrogoSingleRv<T> {
        baseCreateLayoutLinearHorizontal(dividerItem, reverseLayout, stackFromEnd)
        return this
    }

    override fun createLayoutStaggeredGrid(spanCount: Int): FrogoSingleRv<T> {
        baseCreateLayoutStaggeredGrid(spanCount)
        return this
    }

    override fun createLayoutGrid(spanCount: Int): FrogoSingleRv<T> {
        baseCreateLayoutGrid(spanCount)
        return this
    }

    override fun createLayoutFlexBox(flexDirection: Int, justifyContent: Int): FrogoSingleRv<T> {
        baseCreateLayoutFlexBox(flexDirection, justifyContent)
        return this
    }

    override fun addData(listData: List<T>): FrogoSingleRv<T> {
        this.listData.addAll(listData)
        return this
    }

    override fun addDataFH(listDataFH: List<FrogoHolder<T>>): FrogoSingleRv<T> {
        this.listDataFH.addAll(listDataFH)
        frogoViewAdapter.setupMultiHolder()
        return this
    }

    override fun addCustomView(customViewInt: Int): FrogoSingleRv<T> {
        this.customViewId = customViewInt
        return this
    }

    override fun addEmptyView(emptyViewInt: Int?): FrogoSingleRv<T> {
        if (emptyViewInt != null) this.emptyViewId = emptyViewInt
        return this
    }

    override fun addCallback(frogoViewAdapterCallback: IFrogoViewAdapter<T>): FrogoSingleRv<T> {
        this.frogoAdapterCallback = frogoViewAdapterCallback
        return this
    }

    protected open fun createAdapter() {

        if (frogoViewAdapter.hasMultiHolder) {
            optionAdapter = FrogoRvConstant.FROGO_ADAPTER_MULTI
            frogoViewAdapter.setupRequirement(listDataFH)
            frogoViewAdapter.setupEmptyView(emptyViewId)

        } else {
            optionAdapter = FrogoRvConstant.FROGO_ADAPTER_R_CLASS
            frogoViewAdapter.setCallback(object : IFrogoViewHolder<T> {
                override fun setupInitComponent(
                    view: View,
                    data: T,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<T>
                ) {
                    frogoAdapterCallback.setupInitComponent(view, data, position, notifyListener)
                }
            })

            frogoViewAdapter.setupRequirement(customViewId, listData,
                object :
                    FrogoRecyclerViewListener<T> {
                    override fun onItemClicked(
                        view: View,
                        data: T,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<T>
                    ) {
                        frogoAdapterCallback.onItemClicked(view, data, position, notifyListener)
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: T,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<T>
                    ) {
                        frogoAdapterCallback.onItemLongClicked(view, data, position, notifyListener)
                    }

                    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                        return frogoAdapterCallback.areItemsTheSame(oldItem, newItem)
                    }

                    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                        return frogoAdapterCallback.areContentsTheSame(oldItem, newItem)
                    }
                })
            frogoViewAdapter.setupEmptyView(emptyViewId)

        }
    }

    protected open fun setupInnerAdapter() {
        frogoRecycleView.adapter = frogoViewAdapter
    }

    override fun build(): FrogoSingleRv<T> {
        createAdapter()
        setupLayoutManager(frogoRecycleView)
        setupInnerAdapter()
        return this
    }

    override fun frogoNotifyData(): MutableList<T> {
        return frogoViewAdapter.frogoNotifyData()
    }

    override fun frogoNotifyDataSetChanged() {
        frogoViewAdapter.frogoNotifyDataSetChanged()
    }

    override fun frogoNotifyItemChanged(data: T, position: Int, payload: Any) {
        frogoViewAdapter.frogoNotifyItemChanged(data, position, payload)
    }

    override fun frogoNotifyItemChanged(data: T, position: Int) {
        frogoViewAdapter.frogoNotifyItemChanged(data, position)
    }

    override fun frogoNotifyItemInserted(data: T, position: Int) {
        frogoViewAdapter.frogoNotifyItemInserted(data, position)
    }

    override fun frogoNotifyItemMoved(data: T, fromPosition: Int, toPosition: Int) {
        frogoViewAdapter.frogoNotifyItemMoved(data, fromPosition, toPosition)
    }

    override fun frogoNotifyItemRangeChanged(data: List<T>, positionStart: Int, payload: Any) {
        frogoViewAdapter.frogoNotifyItemRangeChanged(data, positionStart, payload)
    }

    override fun frogoNotifyItemRangeChanged(data: List<T>, positionStart: Int) {
        frogoViewAdapter.frogoNotifyItemRangeChanged(data, positionStart)
    }

    override fun frogoNotifyItemRangeInserted(data: List<T>, positionStart: Int) {
        frogoViewAdapter.frogoNotifyItemRangeInserted(data, positionStart)
    }

    override fun frogoNotifyItemRangeRemoved(positionStart: Int, itemCount: Int) {
        frogoViewAdapter.frogoNotifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun frogoNotifyItemRemoved(item: T) {
        frogoViewAdapter.frogoNotifyItemRemoved(item)
    }

}