package com.frogobox.sdk.ui

import android.os.Bundle
import com.frogobox.sdk.databinding.ActivityFrogoAboutUsBinding
import com.frogobox.sdk.view.FrogoBindActivity

open class FrogoAboutUsActivity : FrogoBindActivity<ActivityFrogoAboutUsBinding>() {

    override fun setupViewBinding(): ActivityFrogoAboutUsBinding {
        return ActivityFrogoAboutUsBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        binding.tvCopyright.text = textCopyright
    }

}