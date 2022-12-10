package com.frogobox.sdk.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.frogobox.sdk.databinding.ActivityFrogoWebViewBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.loadUrlExt
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.visible
import com.frogobox.sdk.view.FrogoBindActivity
import com.frogobox.sdk.widget.webview.WebViewCallback

class FrogoWebViewActivity : FrogoBindActivity<ActivityFrogoWebViewBinding>() {

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
        const val EXTRA_TITLE = "EXTRA_TITLE"

        fun startActivityExt(context: Context, url: String, title: String) {
            context.startActivity(Intent(context, FrogoWebViewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_TITLE, title)
            })
        }
    }

    private val url: String by lazy { intent.getStringExtra(EXTRA_URL) ?: "" }

    private val title: String by lazy { intent.getStringExtra(EXTRA_TITLE) ?: "" }

    override fun setupViewBinding(): ActivityFrogoWebViewBinding {
        return ActivityFrogoWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity(title)
        initView()
    }

    private fun initView() {
        binding.apply {
            webView.loadUrlExt(url, object : WebViewCallback {
                override fun onShowProgress() {
                    showLogD<FrogoWebViewActivity>("onShowProgress")
                    progressBar.visible()
                }

                override fun onHideProgress() {
                    showLogD<FrogoWebViewActivity>("onHideProgress")
                    progressBar.gone()
                }

                override fun onFinish() {
                    showLogD<FrogoWebViewActivity>("onFinish")
                }

                override fun onFailed() {
                    showLogD<FrogoWebViewActivity>("onFailed")
                }
            })
        }
    }

}