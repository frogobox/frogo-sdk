package com.frogobox.appsdk.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.BaseActivity
import com.frogobox.appsdk.model.Article
import com.frogobox.databinding.ActivityNewsBinding
import com.frogobox.sdk.ext.progressViewHandle
import dagger.hilt.android.AndroidEntryPoint

abstract class HiltBaseNewsActivity : BaseActivity<ActivityNewsBinding>()

@AndroidEntryPoint(HiltBaseNewsActivity::class)
class NewsActivity : Hilt_NewsActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun setupViewBinding(): ActivityNewsBinding {
        return ActivityNewsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        super.setupViewModel()
        newsViewModel.apply {

            eventShowProgressState.observe(this@NewsActivity) {
                binding.progressCircular.progressViewHandle(it)
            }

            articles.observe(this@NewsActivity) {
                setupRecyclerView(it)
            }

        }
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("News API")
        if (savedInstanceState == null) {
            newsViewModel.onStart()
        }
    }

    private fun setupRecyclerView(data: List<Article>) {
        binding.recyclerView.apply {
            adapter = NewsViewAdapter().apply {
                setItems(data)
            }
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
                stackFromEnd = false
                reverseLayout = false
            }
        }
    }

}