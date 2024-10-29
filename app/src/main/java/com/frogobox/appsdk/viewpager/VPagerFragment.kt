package com.frogobox.appsdk.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.databinding.FragmentVpagerBinding
import com.frogobox.sdk.view.FrogoBindFragment


/*
 * Created by faisalamir on 18/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

class VPagerFragment : FrogoBindFragment<FragmentVpagerBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVpagerBinding {
        return FragmentVpagerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {

    }
}