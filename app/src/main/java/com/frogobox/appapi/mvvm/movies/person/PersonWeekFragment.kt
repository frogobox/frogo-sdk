package com.frogobox.appapi.mvvm.movies.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.databinding.ContentItemBinding
import com.frogobox.databinding.FragmentTrendingChildBinding
import com.frogobox.coreutil.movie.MovieUrl
import com.frogobox.coreutil.movie.model.TrendingMovie
import com.frogobox.coreutil.movie.model.TrendingPerson
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.openDetailImageUri
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonWeekFragment : FrogoBindFragment<FragmentTrendingChildBinding>() {

    private val personViewModel: PersonViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrendingChildBinding {
        return FragmentTrendingChildBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        personViewModel.apply {
            getTrendingPersonWeek()

            eventShowProgressState.observe(viewLifecycleOwner) {
                binding.progressView.progressViewHandle(it)
            }

            eventFailed.observe(viewLifecycleOwner) {
                requireContext().showToast(it)
            }

            listDataWeek.observe(viewLifecycleOwner) {
                setupRV(it)
            }
        }
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
    }

    private fun setupRV(data: List<com.frogobox.coreutil.movie.model.TrendingPerson>) {

        val adapterCallback = object : IFrogoBindingAdapter<com.frogobox.coreutil.movie.model.TrendingPerson, ContentItemBinding> {
            override fun onItemClicked(
                binding: ContentItemBinding,
                data: com.frogobox.coreutil.movie.model.TrendingPerson,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<com.frogobox.coreutil.movie.model.TrendingPerson>
            ) {
            }

            override fun onItemLongClicked(
                binding: ContentItemBinding,
                data: com.frogobox.coreutil.movie.model.TrendingPerson,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<com.frogobox.coreutil.movie.model.TrendingPerson>
            ) {
                requireActivity().openDetailImageUri("${MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.profile_path}")
            }

            override fun areItemsTheSame(
                oldItem: TrendingPerson,
                newItem: TrendingPerson
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TrendingPerson,
                newItem: TrendingPerson
            ): Boolean {
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
                data: com.frogobox.coreutil.movie.model.TrendingPerson,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<com.frogobox.coreutil.movie.model.TrendingPerson>
            ) {
                binding.apply {
                    tvTitle.text = data.name
                    tvOverview.text = data.known_for_department
                    Glide.with(root.context)
                        .load("${com.frogobox.coreutil.movie.MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.profile_path}")
                        .into(ivPoster)
                }
            }
        }

        binding.frogoRecyclerView.injectorBinding<com.frogobox.coreutil.movie.model.TrendingPerson, ContentItemBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()

    }

}


