package com.frogobox.appapi.mvvm.movies.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.databinding.ContentItemBinding
import com.frogobox.databinding.FragmentTrendingChildBinding
import com.frogobox.coreutil.movie.MovieUrl
import com.frogobox.coreutil.movie.model.TrendingMovie
import com.frogobox.coreutil.movie.model.TrendingTv
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.openDetailImageUri
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class TvDayFragment : FrogoBindFragment<FragmentTrendingChildBinding>() {

    private val tvViewModel: TvViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrendingChildBinding {
        return FragmentTrendingChildBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        tvViewModel.apply {
            getTrendingTvDay()

            eventShowProgressState.observe(viewLifecycleOwner) {
                binding.progressView.progressViewHandle(it)
            }

            eventFailed.observe(viewLifecycleOwner) {
                requireContext().showToast(it)
            }

            listDataDay.observe(viewLifecycleOwner) {
                setupRV(it)
            }
        }
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
    }

    private fun setupRV(data: List<com.frogobox.coreutil.movie.model.TrendingTv>) {

        val adapterCallback = object : IFrogoBindingAdapter<com.frogobox.coreutil.movie.model.TrendingTv, ContentItemBinding> {
            override fun onItemClicked(
                binding: ContentItemBinding,
                data: com.frogobox.coreutil.movie.model.TrendingTv,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<com.frogobox.coreutil.movie.model.TrendingTv>
            ) {
                requireActivity().openDetailImageUri("${MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.poster_path}")
            }

            override fun areItemsTheSame(oldItem: TrendingTv, newItem: TrendingTv): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TrendingTv, newItem: TrendingTv): Boolean {
                return oldItem == newItem
            }

            override fun setViewBinding(parent: ViewGroup): ContentItemBinding {
                return ContentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ContentItemBinding,
                data: com.frogobox.coreutil.movie.model.TrendingTv,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<com.frogobox.coreutil.movie.model.TrendingTv>
            ) {
                binding.apply {
                    tvTitle.text = data.name
                    tvOverview.text = data.overview
                    Glide.with(root.context)
                        .load("${MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.poster_path}")
                        .into(ivPoster)
                }
            }
        }

        binding.frogoRecyclerView.injectorBinding<com.frogobox.coreutil.movie.model.TrendingTv, ContentItemBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()

    }

}