package com.frogobox.apprecycler.sample.kotlin.usingadapter.nested

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.databinding.ActivityFrogoRvGridBinding
import com.frogobox.recycler.R
import com.frogobox.recycler.core.FrogoNestedAdapter
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import com.frogobox.recycler.core.FrogoRvConstant
import com.frogobox.recycler.core.IFrogoNestedHolder
import com.frogobox.recycler.core.IFrogoViewHolder

class KotlinSimpleNestedActivity : BaseActivity<ActivityFrogoRvGridBinding>() {

    override fun setupViewBinding(): ActivityFrogoRvGridBinding {
        return ActivityFrogoRvGridBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Simple Nested RecyclerView")
        setupRecyclerView()
    }

    private fun setupData(): MutableList<Int> {
        val subList1 = mutableListOf<Int>()
        for (i in 0..10) {
            subList1.add(i)
        }
        return subList1
    }

    private fun setupDataNested(): MutableList<MutableList<Int>> {
        val list = mutableListOf<MutableList<Int>>()
        for (i in 0..5) {
            list.add(setupData())
        }
        return list
    }

    private fun setupRecyclerView() {

        val mLinearLayoutManager = LinearLayoutManager(this)
        val mAdapter = FrogoNestedAdapter<Int>()
        mAdapter.setCallback(object : IFrogoNestedHolder<Int> {
            override fun nestedCustomView(): Int {
                return R.layout.frogo_list_nested_item
            }

            override fun nestedListener(): FrogoRecyclerViewListener<Int> {
                return object : FrogoRecyclerViewListener<Int> {
                    override fun onItemClicked(
                        view: View,
                        data: Int,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<Int>
                    ) {
                        showToast("Click : $data")
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: Int,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<Int>
                    ) {
                        showToast("Long Click : $data")
                    }

                    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                        return oldItem == newItem
                    }

                    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            override fun nestedCallback(): IFrogoViewHolder<Int> {
                return object : IFrogoViewHolder<Int> {
                    override fun setupInitComponent(
                        view: View,
                        data: Int,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<Int>
                    ) {
                        Glide.with(view.context).load(FrogoRvConstant.LINK_PHOTO_GITHUB)
                            .into(view.findViewById(R.id.image))
                    }
                }
            }
        })
        mAdapter.setupNestedView()
        mAdapter.setupDataNested(setupDataNested())
        binding.frogoRecyclerView.apply {
            layoutManager = mLinearLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

}