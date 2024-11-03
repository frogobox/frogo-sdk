package com.frogobox.appapi.mvvm.movies.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.coreutil.movie.MovieUrl
import com.frogobox.coreutil.movie.model.TrendingPerson
import com.frogobox.databinding.ContentItemBinding
import com.frogobox.databinding.FragmentTrendingChildBinding
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.openDetailImageUri
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PersonDayFragment : FrogoBindFragment<FragmentTrendingChildBinding>() {

    private val personViewModel: PersonViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTrendingChildBinding {
        return FragmentTrendingChildBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        personViewModel.apply {
            getTrendingPersonDay()

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

    private fun setupRV(data: List<TrendingPerson>) {

        val adapterCallback = object :
            IFrogoBindingAdapter<TrendingPerson, ContentItemBinding> {
            override fun onItemClicked(
                binding: ContentItemBinding,
                data: TrendingPerson,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<TrendingPerson>,
            ) {
                requireActivity().openDetailImageUri("${MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.profile_path}")
            }

            override fun areItemsTheSame(
                oldItem: TrendingPerson,
                newItem: TrendingPerson,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TrendingPerson,
                newItem: TrendingPerson,
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
                data: TrendingPerson,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<TrendingPerson>,
            ) {
                binding.apply {
                    tvTitle.text = data.name
                    tvOverview.text = data.known_for_department
                    Glide.with(root.context)
                        .load("${MovieUrl.BASE_URL_IMAGE_ORIGNAL}${data.profile_path}")
                        .into(ivPoster)
                }
            }
        }

        binding.frogoRecyclerView.injectorBinding<TrendingPerson, ContentItemBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()

    }

}