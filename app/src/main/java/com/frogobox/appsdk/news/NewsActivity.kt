package com.frogobox.appsdk.news

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsBinding
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
                for (i in it.indices) {
                    println("$i : ${it[i].title}")
                }
            }

            getData()

        }
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        newsViewModel.onClearDisposable()
    }

}