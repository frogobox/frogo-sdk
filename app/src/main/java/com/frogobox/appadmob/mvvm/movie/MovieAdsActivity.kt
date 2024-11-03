package com.frogobox.appadmob.mvvm.movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.frogobox.admob.core.FrogoAdmob
import com.frogobox.api.movie.ConsumeMovieApi
import com.frogobox.BuildConfig
import com.frogobox.R
import com.frogobox.appadmob.base.BaseActivity
import com.frogobox.databinding.ActivityRecyclerViewAdmobBinding
import com.frogobox.coreapi.ConsumeApiResponse
import com.frogobox.coreutil.movie.MovieUrl
import com.frogobox.coreutil.movie.model.TrendingMovie
import com.frogobox.coreutil.movie.response.Trending
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import com.google.android.gms.ads.AdView

class MovieAdsActivity : BaseActivity<ActivityRecyclerViewAdmobBinding>() {

    val arrayFrogoAdmobData = mutableListOf<Any>()

    override fun setupViewBinding(): ActivityRecyclerViewAdmobBinding {
        return ActivityRecyclerViewAdmobBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("RecyclerView (2)")
        setupNewsApi()
    }

    private fun setupNewsApi() {
        val consumeMovieApi =
            ConsumeMovieApi(MovieUrl.API_KEY).usingChuckInterceptor(BuildConfig.DEBUG, this)
        consumeMovieApi.getTrendingMovieWeek( // Adding Base Parameter on main function

            object : ConsumeApiResponse<Trending<TrendingMovie>> {

                override fun onShowProgress() {
                    // Your Progress Show
                }

                override fun onHideProgress() {
                    // Your Progress Hide
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    // Your failed to do
                }

                override fun onFinish() {

                }

                override fun onSuccess(data: Trending<TrendingMovie>) {
                    data.results?.let { arrayFrogoAdmobData.addAll(it) }
                    FrogoAdmob.loadRecyclerBannerAds(
                        getString(R.string.admob_banner),
                        this@MovieAdsActivity,
                        arrayFrogoAdmobData
                    )
                    setupRecyclerView()
                }

            })
    }

    private fun setupAdapter(): MovieAdapter {
        val adapter = MovieAdapter()
        adapter.setupRequirement(R.layout.content_item_news, arrayFrogoAdmobData, object :
            FrogoRecyclerViewListener<Any> {
            override fun onItemClicked(
                view: View,
                data: Any,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Any>
            ) {
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

        })
        return adapter
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MovieAdsActivity, 2)
            adapter = setupAdapter()
        }
    }

    override fun onResume() {
        super.onResume()
        for (item in arrayFrogoAdmobData) {
            if (item is AdView) {
                item.resume()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        for (item in arrayFrogoAdmobData) {
            if (item is AdView) {
                item.pause()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (item in arrayFrogoAdmobData) {
            if (item is AdView) {
                item.destroy()
            }
        }

    }


}