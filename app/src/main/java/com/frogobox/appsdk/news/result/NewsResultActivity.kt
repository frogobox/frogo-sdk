package com.frogobox.appsdk.news.result

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.BaseActivity
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.news.NewsViewAdapter
import com.frogobox.coresdk.source.Resource
import com.frogobox.databinding.ActivityNewsBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.visible
import dagger.hilt.android.AndroidEntryPoint

abstract class HiltBaseNewsResultActivity : BaseActivity<ActivityNewsBinding>()

@AndroidEntryPoint(HiltBaseNewsResultActivity::class)
class NewsResultActivity : Hilt_NewsResultActivity() {

    private val newsViewModel: NewsResultViewModel by viewModels()

    override fun setupViewBinding(): ActivityNewsBinding {
        return ActivityNewsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        super.setupViewModel()
        newsViewModel.apply {

            articles.observe(this@NewsResultActivity) {
                when (it) {
                    is Resource.Error -> {
                        binding.progressCircular.gone()
                    }

                    is Resource.Loading -> {
                        binding.progressCircular.visible()
                    }

                    is Resource.Success -> {
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

}