package com.frogobox.apprecycler.sample.kotlin.noadapter.multiview

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.apprecycler.model.ExampleModel
import com.frogobox.databinding.ActivityFrogoRvGridBinding
import com.frogobox.recycler.core.FrogoHolder
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import com.frogobox.recycler.core.FrogoRvConstant
import com.frogobox.recycler.core.IFrogoViewHolder
import com.frogobox.ui.R

class KotlinNoAdapterMultiVewActivity : BaseActivity<ActivityFrogoRvGridBinding>() {

    override fun setupViewBinding(): ActivityFrogoRvGridBinding {
        return ActivityFrogoRvGridBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFrogoRecyclerView()
        setupDetailActivity("Kotlin No Adapter - Multi View")
    }

    private fun firstCallback(): IFrogoViewHolder<ExampleModel> {
        return object : IFrogoViewHolder<ExampleModel> {
            override fun setupInitComponent(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // Init component content item recyclerview
                view.findViewById<TextView>(R.id.frogo_rv_grid_type_1_tv_title).text = data.name
                Glide.with(view.context).load(FrogoRvConstant.LINK_PHOTO_GITHUB).into(view.findViewById(R.id.frogo_rv_grid_type_1_iv_poster))
            }
        }
    }

    private fun secondCallback(): IFrogoViewHolder<ExampleModel> {
        return object : IFrogoViewHolder<ExampleModel>{
            override fun setupInitComponent(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // Init component content item recyclerview
                view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_title).text = data.name
                view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_subtitle).text = data.name
                view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_desc).text = FrogoRvConstant.DUMMY_LOREM_IPSUM

                Glide.with(view.context).load(FrogoRvConstant.LINK_PHOTO_GITHUB).into(view.findViewById<ImageView>(R.id.frogo_rv_grid_type_3_iv_poster))
            }
        }
    }

    private fun firstListenerType(): FrogoRecyclerViewListener<ExampleModel>{
        return object : FrogoRecyclerViewListener<ExampleModel>{
            override fun onItemClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                showToast(data.name + " First")
            }

            override fun onItemLongClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                showToast("LAYOUT TYPE 1")
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun secondListenerType() : FrogoRecyclerViewListener<ExampleModel>{
        return object : FrogoRecyclerViewListener<ExampleModel>{
            override fun onItemClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                showToast(data.name + " Second")
            }

            override fun onItemLongClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                showToast("LAYOUT TYPE 2")
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun data() : MutableList<FrogoHolder<ExampleModel>> {
        val data =  mutableListOf<FrogoHolder<ExampleModel>>()
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_1, FrogoRvConstant.OPTION_HOLDER_FIRST, firstCallback(), firstListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_1, FrogoRvConstant.OPTION_HOLDER_FIRST, firstCallback(), firstListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_1, FrogoRvConstant.OPTION_HOLDER_FIRST, firstCallback(), firstListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_3, FrogoRvConstant.OPTION_HOLDER_SECOND, secondCallback(), secondListenerType()))
//        data.add(FrogoHolder(ExampleModel(Constant.FULL_NAME), R.layout.frogo_rv_grid_type_1, FrogoRvConstant.OPTION_HOLDER_FIRST, firstCallback(), firstListenerType()))
        return data
    }

    private fun setupFrogoRecyclerView() {
        binding.frogoRecyclerView
            .injector<ExampleModel>()
            .addDataFH(data())
            .createLayoutStaggeredGrid(2)
            .build()
    }

}