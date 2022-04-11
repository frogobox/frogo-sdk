package com.frogobox.appsdk.news

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsDetailBinding
import com.frogobox.appsdk.model.Article
import com.frogobox.sdk.ext.glideLoad
import com.frogobox.sdk.ext.singleGetExtraData

class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding>() {

    private val extraData: Article by lazy {
        singleGetExtraData("EXTRA_NEWS_DETAIL")
    }

    override fun setupViewBinding(): ActivityNewsDetailBinding {
        return ActivityNewsDetailBinding.inflate(layoutInflater)
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        setupDetailActivity("Detail News")
        binding.apply {
            ivNewsDetailImage.glideLoad(extraData.urlToImage)
            newsDetailTitle.text = extraData.title
            newsDetailDescription.text = extraData.description
        }
    }
}