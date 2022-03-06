package com.frogobox.appsdk

import android.os.Bundle
import com.frogobox.appsdk.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        setupHideSystemUI()
        binding.apply {
            tv.text = if (isNetworkConnected()) {
                "Internet Connected"
            } else {
                "Disconnet from the Internet"
            }
            tv.setOnClickListener {
                shareApp(packageName, getString(R.string.app_name))
            }
        }
    }

}