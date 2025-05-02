package com.frogobox.apprecycler.sample.kotlin.usingadapter.simple

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.apprecycler.model.ExampleModel
import com.frogobox.apprecycler.util.Constant
import com.frogobox.databinding.ActivityFrogoRvListBinding
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import com.frogobox.ui.R

class KotlinSampleActivity : BaseActivity<ActivityFrogoRvListBinding>() {

    private val dummyData = Constant.dummyData(Constant.FULL_NAME)

    override fun setupViewBinding(): ActivityFrogoRvListBinding {
        return ActivityFrogoRvListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        setupDetailActivity("Kotlin With Adapter")
    }

    private fun setupAdapter() {
        val adapter = KotlinSampleViewAdapter()
        adapter.setupRequirement(
            R.layout.frogo_rv_list_type_1,
            dummyData,
            object : FrogoRecyclerViewListener<ExampleModel> {
                override fun onItemClicked(
                    view: View,
                    data: ExampleModel,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
                ) {
                    Toast.makeText(this@KotlinSampleActivity, data.name, Toast.LENGTH_SHORT).show()
                }

                override fun onItemLongClicked(
                    view: View,
                    data: ExampleModel,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
                ) {
                    Toast.makeText(this@KotlinSampleActivity, data.name, Toast.LENGTH_SHORT).show()
                }

                override fun areItemsTheSame(
                    oldItem: ExampleModel,
                    newItem: ExampleModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: ExampleModel,
                    newItem: ExampleModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
        )
        adapter.setupEmptyView(R.layout.frogo_container_empty_view) // With Custom View
        binding.frogoRecyclerView.adapter = adapter
        binding.frogoRecyclerView.isViewLinearVertical(false)
    }

}