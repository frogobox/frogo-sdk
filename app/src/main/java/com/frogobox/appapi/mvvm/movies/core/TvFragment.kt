package com.frogobox.appapi.mvvm.movies.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.R
import com.frogobox.appapi.mvvm.movies.tv.TvDayFragment
import com.frogobox.appapi.mvvm.movies.tv.TvWeekFragment
import com.frogobox.appapi.util.PagerAdapter
import com.frogobox.databinding.FragmentTrendingBinding
import com.frogobox.sdk.view.FrogoBindFragment

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : FrogoBindFragment<FragmentTrendingBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTrendingBinding {
        return FragmentTrendingBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.setupPagerFragment(
            TvDayFragment(),
            resources.getString(R.string.title_day)
        )
        pagerAdapter.setupPagerFragment(
            TvWeekFragment(),
            resources.getString(R.string.title_week)
        )

        binding.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}
