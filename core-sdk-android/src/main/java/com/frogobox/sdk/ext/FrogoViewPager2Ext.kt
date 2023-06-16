package com.frogobox.sdk.ext

import android.content.Context
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.frogobox.sdk.util.FrogoPagerHelper2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by Faisal Amir on 12/01/23
 * Copyright (C) Frogobox
 */


fun ViewPager2.getTitles(): List<String> {
    val viewPager2Adapter = this.adapter as FrogoPagerHelper2
    return viewPager2Adapter.getTitles()
}

fun TabLayout.setupWithViewPager2(viewPager2: ViewPager2) {
    TabLayoutMediator(this, viewPager2) { tab, position ->
        tab.text = viewPager2.getTitles()[position]
    }.attach()
}

fun <VB : ViewBinding> TabLayout.setupCustomViewPager2(
    viewPager: ViewPager2,
    listener: ViewPagerListener<VB>,
) {
    for (i in 0 until this.tabCount) {
        this.getTabAt(i)?.customView = viewPager.getTabView(i, listener).root
    }
}

fun <VB : ViewBinding> ViewPager2.getTabView(position: Int, listener: ViewPagerListener<VB>): VB {
    val binding: VB = listener.getViewBinding(this.context)
    listener.setupComponent(this.getTitles(), position, binding)
    return binding
}

interface ViewPagerListener<VB> {
    fun getViewBinding(context: Context): VB
    fun setupComponent(titles: List<String>, position: Int, binding: VB)
}