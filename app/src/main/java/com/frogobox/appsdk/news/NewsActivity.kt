package com.frogobox.appsdk.news

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsBinding
import com.frogobox.appsdk.model.Article
import com.frogobox.sdk.ext.progressViewHandle
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity<ActivityNewsBinding>() {

    private val newsViewModel : NewsViewModel by viewModel()

    override fun setupViewBinding(): ActivityNewsBinding {
        return ActivityNewsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        super.setupViewModel()
        newsViewModel.apply {

            listData.observe(this@NewsActivity) {
                setupRecyclerView(it)
            }

            eventShowProgress.observe(this@NewsActivity) {
                binding.progressCircular.progressViewHandle(it)
            }

            getData()
            getPref()
        }
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        setupDetailActivity("News API")
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