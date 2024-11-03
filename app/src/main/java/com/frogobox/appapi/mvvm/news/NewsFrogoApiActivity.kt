package com.frogobox.appapi.mvvm.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.coreutil.news.NewsConstant
import com.frogobox.coreutil.news.model.Article
import com.frogobox.databinding.ActivityNewsFrogoApiBinding
import com.frogobox.databinding.ContentArticleHorizontalBinding
import com.frogobox.databinding.ContentArticleVerticalBinding
import com.frogobox.databinding.ContentCategoryBinding
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.setImageExt
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.sdk.ext.toJson
import com.frogobox.sdk.view.FrogoBindActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFrogoApiActivity : FrogoBindActivity<ActivityNewsFrogoApiBinding>() {

    private val newsViewModel: NewsViewModel by viewModel()

    override fun setupViewBinding(): ActivityNewsFrogoApiBinding {
        return ActivityNewsFrogoApiBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        newsViewModel.apply {

            getTopHeadline()
            getTopHeadline(NewsConstant.CATEGORY_HEALTH)
            setupCategory()

            eventShowProgressState.observe(this@NewsFrogoApiActivity) {
                binding.progressView.progressViewHandle(it)
            }

            eventFailed.observe(this@NewsFrogoApiActivity) {
                showToast(it)
            }

            listData.observe(this@NewsFrogoApiActivity) {
                setupRvHeader(it)
            }

            listCategory.observe(this@NewsFrogoApiActivity) {
                setupRvCategory(it)
            }

            listDataCategory.observe(this@NewsFrogoApiActivity) {
                setupRvBody(it)
            }

        }
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("News API")
    }

    private fun setupRvCategory(data: List<String>) {

        val callback = object : IFrogoBindingAdapter<String, ContentCategoryBinding> {
            override fun onItemClicked(
                binding: ContentCategoryBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>,
            ) {
                binding.tvCategory.text = "category $data"
                newsViewModel.getTopHeadline(data)
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun setViewBinding(parent: ViewGroup): ContentCategoryBinding {
                return ContentCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ContentCategoryBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>,
            ) {
                binding.tvCategory.text = data
            }
        }

        binding.rvCategory.injectorBinding<String, ContentCategoryBinding>()
            .addData(data)
            .addCallback(callback)
            .createLayoutLinearHorizontal(false)
            .build()
    }

    private fun setupRvHeader(data: List<Article>) {

        val callback = object : IFrogoBindingAdapter<Article, ContentArticleHorizontalBinding> {
            override fun onItemClicked(
                binding: ContentArticleHorizontalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                startActivityExt<NewsDetailFrogoApiActivity> {
                    it.putExtra(NewsDetailFrogoApiActivity.EXTRA_DATA, data.toJson())
                }
            }

            override fun onItemLongClicked(
                binding: ContentArticleHorizontalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                data.description?.let { showToast(it) }
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun setViewBinding(parent: ViewGroup): ContentArticleHorizontalBinding {
                return ContentArticleHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ContentArticleHorizontalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                binding.apply {
                    tvTitle.text = data.title
                    tvPublished.text = data.publishedAt
                    tvDescription.text = data.description
                    ivUrl.setImageExt(data.urlToImage)
                }
            }
        }

        binding.rvNewsGeneral.injectorBinding<Article, ContentArticleHorizontalBinding>()
            .addData(data)
            .addCallback(callback)
            .createLayoutLinearHorizontal(false)
            .build()

    }

    private fun setupRvBody(data: List<Article>) {

        val callback = object : IFrogoBindingAdapter<Article, ContentArticleVerticalBinding> {
            override fun onItemClicked(
                binding: ContentArticleVerticalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                startActivityExt<NewsDetailFrogoApiActivity> {
                    it.putExtra(NewsDetailFrogoApiActivity.EXTRA_DATA, data.toJson())
                }
            }

            override fun onItemLongClicked(
                binding: ContentArticleVerticalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                data.description?.let { showToast(it) }
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun setViewBinding(parent: ViewGroup): ContentArticleVerticalBinding {
                return ContentArticleVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ContentArticleVerticalBinding,
                data: Article,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Article>,
            ) {
                binding.apply {
                    tvTitle.text = data.title
                    tvPublished.text = data.publishedAt
                    tvDescription.text = data.description
                    Glide.with(root.context).load(data.urlToImage).into(ivUrl)
                }
            }
        }

        binding.rvNewsCategory.injectorBinding<Article, ContentArticleVerticalBinding>()
            .addData(data)
            .addCallback(callback)
            .createLayoutLinearVertical(false)
            .build()
    }

}