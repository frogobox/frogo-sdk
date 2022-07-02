package com.frogobox.sdk.ui

import android.os.Bundle
import com.frogobox.sdk.databinding.ActivityFrogoAboutUsBinding
import com.frogobox.sdk.view.FrogoBindActivity

class FrogoAboutUsActivity : FrogoBindActivity<ActivityFrogoAboutUsBinding>() {

    override fun setupViewBinding(): ActivityFrogoAboutUsBinding {
        return ActivityFrogoAboutUsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("About Frogobox")
        binding.tvCopyright.text = textCopyright
    }

}