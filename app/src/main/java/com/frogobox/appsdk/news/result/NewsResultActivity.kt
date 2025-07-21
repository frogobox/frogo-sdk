package com.frogobox.appsdk.news.result

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.news.NewsViewAdapter
import com.frogobox.coresdk.source.FrogoResult
import com.frogobox.databinding.ActivityNewsBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.visible
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
                    is FrogoResult.Error -> {
                        binding.progressCircular.gone()
                    }

                    is FrogoResult.Loading -> {
                        binding.progressCircular.visible()
                    }

                    is FrogoResult.Success -> {
                        binding.progressCircular.gone()
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