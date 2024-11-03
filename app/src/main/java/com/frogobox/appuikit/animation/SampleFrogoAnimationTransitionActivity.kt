package com.frogobox.appuikit.animation

import android.os.Bundle
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.databinding.ActivitySampleFrogoAnimationTransitionBinding

class SampleFrogoAnimationTransitionActivity :
    BaseActivity<ActivitySampleFrogoAnimationTransitionBinding>() {

    override fun setupViewBinding(): ActivitySampleFrogoAnimationTransitionBinding {
        return ActivitySampleFrogoAnimationTransitionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {

        }

    }

}