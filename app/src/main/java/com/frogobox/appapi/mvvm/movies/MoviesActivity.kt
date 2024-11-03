package com.frogobox.appapi.mvvm.movies

import android.os.Bundle
import com.frogobox.R
import com.frogobox.appapi.mvvm.movies.core.MovieFragment
import com.frogobox.appapi.mvvm.movies.core.PersonFragment
import com.frogobox.appapi.mvvm.movies.core.TvFragment
import com.frogobox.databinding.ActivityMovieBinding
import com.frogobox.sdk.view.FrogoBindActivity

class MoviesActivity : FrogoBindActivity<ActivityMovieBinding>() {

    override fun setupViewBinding(): ActivityMovieBinding {
        return ActivityMovieBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("Movie Api")
        setupToolbar()
        setupBottomNav(binding.framelayoutMainContainer.id)
        setupFragment(savedInstanceState)
    }

    private fun setupToolbar() {
        supportActionBar?.elevation = 0f
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavMainMenu.selectedItemId = R.id.bottom_menu_tv
        }
    }

    private fun setupBottomNav(frameLayout: Int) {
        binding.bottomNavMainMenu.apply {
            clearAnimation()
            itemIconTintList = null

            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_menu_movie -> {
                        supportActionBar?.title = getString(R.string.title_movie)
                        setupChildFragment(
                            frameLayout,
                            MovieFragment()
                        )
                    }

                    R.id.bottom_menu_person -> {
                        supportActionBar?.title = getString(R.string.title_person)
                        setupChildFragment(
                            frameLayout,
                            PersonFragment()
                        )
                    }

                    R.id.bottom_menu_tv -> {
                        supportActionBar?.title = getString(R.string.title_tv)
                        setupChildFragment(
                            frameLayout,
                            TvFragment()
                        )
                    }
                }

                true
            }
        }

    }

}