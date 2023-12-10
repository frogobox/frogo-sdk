package com.frogobox.appsdk.news.result

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsBinding
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.news.NewsViewAdapter
import com.frogobox.coresdk.source.FrogoResult
import com.frogobox.sdk.ext.progressViewHandle
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsResultActivity : BaseActivity<ActivityNewsBinding>() {

    private val newsViewModel: NewsResultViewModel by viewModel()

    override fun setupViewBinding(): ActivityNewsBinding {
        return ActivityNewsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        super.setupViewModel()
        newsViewModel.apply {

            articles.observe(this@NewsResultActivity) {
                when (it) {
                    is FrogoResult.Error -> {}
                    is FrogoResult.Finish -> {}
                    is FrogoResult.Loading -> {
                        binding.progressCircular.progressViewHandle(it.isLoading)
                    }
                    is FrogoResult.Success -> {
                        it.result.articles?.let { list ->
                            setupRecyclerView(list)
                        }
                    }
                }
                
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
                setupData(data)
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