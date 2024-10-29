package com.frogobox.appuikit.loadingindicator

import android.os.Bundle
import android.view.View
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.databinding.ActivityDetailLoadingIndicatorViewBinding

/**
 * Created by Jack Wang on 2016/8/5.
 */
class DetailLoadingIndicatorActivity : BaseActivity<ActivityDetailLoadingIndicatorViewBinding>() {

    override fun setupViewBinding(): ActivityDetailLoadingIndicatorViewBinding {
        return ActivityDetailLoadingIndicatorViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val indicator = intent.getStringExtra("indicator")
        binding.fli.setIndicator(indicator)
    }

    fun hideClick(view: View?) {
        binding.fli.hide()
        // or fli.smoothToHide();
    }

    fun showClick(view: View?) {
        binding.fli.show()
        // or fli.smoothToShow();
    }

}