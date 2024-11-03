package com.frogobox

import android.os.Bundle
import com.frogobox.appapi.mvvm.main.MainFrogoApiActivity
import com.frogobox.appsdk.main.MainSDKActivity
import com.frogobox.appuikit.MainUIActivity
import com.frogobox.databinding.ActivityMainBinding
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.sdk.view.FrogoBindActivity

/**
 * Created by faisalamircs on 29/10/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


class MainActivity : FrogoBindActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

            btnSdk.setOnClickListener {
                startActivityExt<MainSDKActivity> {

                }
            }

            btnUi.setOnClickListener {
                startActivityExt<MainUIActivity> {

                }
            }

            btnApi.setOnClickListener {
                startActivityExt<MainFrogoApiActivity> {

                }
            }

        }
    }

}