package com.frogobox.appsdk.news

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.model.Article
import com.frogobox.databinding.ActivityNewsBinding
import com.frogobox.sdk.ext.progressViewHandle
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity<ActivityNewsBinding>() {

    private val newsViewModel: NewsViewModel by viewModel()

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

    override fun onDestroy() {
        super.onDestroy()
        newsViewModel.onClearDisposable()
    }

}