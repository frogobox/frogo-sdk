package com.frogobox.sdk.ui

import android.os.Bundle
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.frogobox.sdk.R
import com.frogobox.sdk.databinding.ActivityFrogoCustomCrashBinding
import com.frogobox.sdk.ext.copyToClipboard
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindActivity

open class FrogoCustomCrashActivity : FrogoBindActivity<ActivityFrogoCustomCrashBinding>() {

    override fun setupViewBinding(): ActivityFrogoCustomCrashBinding {
        return ActivityFrogoCustomCrashBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)

        val config = CustomActivityOnCrash.getConfigFromIntent(intent)

        if (config == null) {
            finish()
            return
        }

        binding.errorDetails.setOnLongClickListener {
            binding.errorDetails.copyToClipboard()
            showToast("Copied to clipboard")
            true
        }

        binding.apply {
            errorDetails.text = CustomActivityOnCrash.getStackTraceFromIntent(intent)

            if (config.isShowRestartButton && config.restartActivityClass != null) {
                restartButton.text = getString(R.string.restart_app)
                restartButton.setOnClickListener {
                    CustomActivityOnCrash.restartApplication(
                        this@FrogoCustomCrashActivity,
                        config
                    )
                }
            } else {
                restartButton.setOnClickListener {
                    CustomActivityOnCrash.closeApplication(
                        this@FrogoCustomCrashActivity,
                        config
                    )
                }
            }
        }
    }

}