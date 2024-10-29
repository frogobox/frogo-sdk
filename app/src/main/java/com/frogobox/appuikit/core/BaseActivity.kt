package com.frogobox.appuikit.core

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/*
 * Created by faisalamir on 20/05/21
 * FrogoUIKit
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract fun setupViewBinding() : VB

    protected val binding: VB by lazy {
        setupViewBinding()
    }

    protected val mActivity: AppCompatActivity by lazy {
        this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    protected fun setupDetailActivity(title: String) {
        supportActionBar?.title = title
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun setupTabTitles(
        tabLayout: TabLayout,
        viewPager2: ViewPager2,
        titles: MutableList<String>
    ) {
        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
    }

}