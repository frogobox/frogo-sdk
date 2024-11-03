package com.frogobox.appapi.mvvm.movies.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.R
import com.frogobox.databinding.FragmentTrendingBinding
import com.frogobox.appapi.mvvm.movies.movie.MovieDayFragment
import com.frogobox.appapi.mvvm.movies.movie.MovieWeekFragment
import com.frogobox.appapi.util.PagerAdapter
import com.frogobox.sdk.view.FrogoBindFragment

class MovieFragment : FrogoBindFragment<FragmentTrendingBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrendingBinding {
        return FragmentTrendingBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.setupPagerFragment(
            MovieDayFragment(),
            resources.getString(R.string.title_day)
        )
        pagerAdapter.setupPagerFragment(
            MovieWeekFragment(),
            resources.getString(R.string.title_week)
        )

        binding.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}
