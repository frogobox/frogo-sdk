package com.frogobox.sdk.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.frogobox.sdk.ext.showLogDebug

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
@Deprecated("Use ViewPager2 and FrogoPagerHelper2 instead")
class FrogoPagerHelper(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager),
    IFrogoPagerHelper {

    companion object {
        val TAG: String = FrogoPagerHelper::class.java.simpleName
    }

    private val fragments = mutableListOf<Fragment>()

    private val titles = mutableListOf<String>()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getTitles(): List<String> {
        return titles
    }

    override fun getFragments(): List<Fragment> {
        return fragments
    }

    override fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

}