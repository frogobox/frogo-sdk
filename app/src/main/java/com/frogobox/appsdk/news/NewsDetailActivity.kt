package com.frogobox.appsdk.news

import android.os.Bundle
import com.frogobox.appsdk.R
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsDetailBinding
import com.frogobox.appsdk.model.Article
import com.frogobox.sdk.ext.getExtraDataExt
import com.frogobox.sdk.ext.setImageExt

class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding>() {

    private val extraData: Article by lazy {
        getExtraDataExt("EXTRA_NEWS_DETAIL")
    }

    override fun setupViewBinding(): ActivityNewsDetailBinding {
        return ActivityNewsDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("Detail News")
        binding.apply {
            ivNewsDetailImage.setImageExt(extraData.urlToImage, R.drawable.ic_frogobox)
            newsDetailTitle.text = extraData.title
            newsDetailDescription.text = extraData.description
        }
    }
}