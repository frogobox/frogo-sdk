package com.frogobox.appuikit.animation

import android.content.Intent
import android.os.Bundle
import com.frogobox.ui.animation.FrogoAnimation
import com.frogobox.ui.animation.core.Attention
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.databinding.ActivitySampleFrogoAnimationBinding

class SampleFrogoAnimationActivity : BaseActivity<ActivitySampleFrogoAnimationBinding>() {

    override fun setupViewBinding(): ActivitySampleFrogoAnimationBinding {
        return ActivitySampleFrogoAnimationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Frogo Animation")

        FrogoAnimation().apply {
            setAnimation(Attention.Ruberband(binding.textView))
            setRepeated()
            setDuration(1500)
        }.start()

        binding.btnTransition.setOnClickListener {
            startActivity(
                Intent(
                    this@SampleFrogoAnimationActivity,
                    SampleFrogoAnimationTransitionActivity::class.java
                )
            )
        }

    }


}