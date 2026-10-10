package com.frogobox.appuikit

import android.content.Intent
import android.os.Bundle
import com.frogobox.BaseActivity
import com.frogobox.appuikit.animation.SampleFrogoAnimationActivity
import com.frogobox.appuikit.loadingindicator.SampleFrogoLoadingIndicatorViewActivity
import com.frogobox.databinding.ActivityMainFrogoUiBinding

class MainUIActivity : BaseActivity<ActivityMainFrogoUiBinding>() {

    override fun setupViewBinding(): ActivityMainFrogoUiBinding {
        return ActivityMainFrogoUiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Frogo UI")
        setupActionListeners()
    }

    private fun setupActionListeners() {
        binding.apply {
            cardFrogoAnimation.setOnClickListener {
                startActivity(Intent(this@MainUIActivity, SampleFrogoAnimationActivity::class.java))
            }

            cardFrogoLoadingIndicator.setOnClickListener {
                startActivity(Intent(this@MainUIActivity, SampleFrogoLoadingIndicatorViewActivity::class.java))
            }
        }
    }
}