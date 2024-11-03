package com.frogobox.appapi.mvvm.news

import android.os.Bundle
import com.bumptech.glide.Glide
import com.frogobox.coreutil.news.model.Article
import com.frogobox.databinding.ActivityNewsDetailFrogoApiBinding
import com.frogobox.sdk.ext.getExtraExt
import com.frogobox.sdk.view.FrogoBindActivity

class NewsDetailFrogoApiActivity : FrogoBindActivity<ActivityNewsDetailFrogoApiBinding>() {

    companion object {
        const val EXTRA_DATA = "com.frogobox.newsapp.activity.DetailActivity.extra_data"
    }

    override fun setupViewBinding(): ActivityNewsDetailFrogoApiBinding {
        return ActivityNewsDetailFrogoApiBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}


    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("Detail Berita")

        val extraArticle = getExtraExt<Article>(EXTRA_DATA)
        binding.apply {
            tvTitle.text = extraArticle.title
            tvSource.text = extraArticle.source?.name ?: ""
            tvContent.text = extraArticle.description
            Glide.with(this@NewsDetailFrogoApiActivity).load(extraArticle.urlToImage).into(ivUrl)
        }
    }


}
