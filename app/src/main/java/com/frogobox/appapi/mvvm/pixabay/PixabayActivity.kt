package com.frogobox.appapi.mvvm.pixabay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.databinding.ActivityPixabayBinding
import com.frogobox.databinding.ItemGridImageBinding
import com.frogobox.coreutil.news.model.Article
import com.frogobox.coreutil.pixabay.model.PixabayImage
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PixabayActivity : FrogoBindActivity<ActivityPixabayBinding>() {

    private val pixabayViewModel: PixabayViewModel by viewModel()

    override fun setupViewBinding(): ActivityPixabayBinding {
        return ActivityPixabayBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        pixabayViewModel.apply {

            searchImage(this@PixabayActivity, "Nature")

            eventShowProgressState.observe(this@PixabayActivity) {
                binding.progressBar.progressViewHandle(it)
            }

            eventFailed.observe(this@PixabayActivity) {
                showToast(it)
            }

            listData.observe(this@PixabayActivity) {
                setupRv(it)
            }

        }
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("Pixabay Api")
    }

    private fun setupRv(data: List<PixabayImage>) {

        val adapterCallback = object : IFrogoBindingAdapter<PixabayImage, ItemGridImageBinding> {
            override fun onItemClicked(
                binding: ItemGridImageBinding,
                data: PixabayImage,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<PixabayImage>
            ) {
            }

            override fun areItemsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
                return oldItem == newItem
            }

            override fun setViewBinding(parent: ViewGroup): ItemGridImageBinding {
                return ItemGridImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ItemGridImageBinding,
                data: PixabayImage,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<PixabayImage>
            ) {
                val totalLikes = "${data.likes} likes"
                binding.apply {
                    Glide.with(root.context).load(data.previewURL).into(ivIcon)
                    tvTitle.text = totalLikes
                    tvSub.text = data.user
                }
            }
        }

        binding.frogoRv.injectorBinding<PixabayImage, ItemGridImageBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()
    }

}