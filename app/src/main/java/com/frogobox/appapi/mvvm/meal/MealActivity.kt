package com.frogobox.appapi.mvvm.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.databinding.ActivityMealBinding
import com.frogobox.databinding.ItemGridImageBinding
import com.frogobox.coreutil.meal.model.Meal
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.openDetailImageUri
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.view.FrogoBindActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealActivity : FrogoBindActivity<ActivityMealBinding>() {

    private val mealViewModel: MealViewModel by viewModel()

    override fun setupViewBinding(): ActivityMealBinding {
        return ActivityMealBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mealViewModel.apply {

            eventShowProgressState.observe(this@MealActivity) {
                binding.progressBar.progressViewHandle(it)
            }

            eventFailed.observe(this@MealActivity) {
                showToast(it)
            }

            listData.observe(this@MealActivity) {
                setupRv(it)
            }

        }

    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("Meal Api")
        mealViewModel.getListMeals(this, "b")
    }

    private fun setupRv(data: List<Meal>) {

        val adapterCallback = object : IFrogoBindingAdapter<Meal, ItemGridImageBinding> {
            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.idMeal == newItem.idMeal
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
                data: Meal,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Meal>
            ) {
                binding.apply {
                    Glide.with(root.context).load(data.strMealThumb).into(ivIcon)
                    tvTitle.text = data.strMeal
                    tvSub.text = data.strCategory
                }
            }

            override fun onItemClicked(
                binding: ItemGridImageBinding,
                data: Meal,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Meal>
            ) {
                openDetailImageUri(data.strMealThumb ?: "")
            }

            override fun onItemLongClicked(
                binding: ItemGridImageBinding,
                data: Meal,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Meal>
            ) {
                data.strMeal?.let { showToast(it) }
            }

        }

        binding.frogoRv.injectorBinding<Meal, ItemGridImageBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()
    }

}