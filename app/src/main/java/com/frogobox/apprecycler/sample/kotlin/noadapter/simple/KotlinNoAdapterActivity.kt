package com.frogobox.apprecycler.sample.kotlin.noadapter.simple

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.apprecycler.model.ExampleModel
import com.frogobox.apprecycler.util.Constant
import com.frogobox.apprecycler.util.FLog
import com.frogobox.databinding.ActivityBaseBinding
import com.frogobox.recycler.core.FrogoLayoutManager
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.recycler.core.IFrogoBuilderRv
import com.frogobox.recycler.core.IFrogoBuilderRvBinding
import com.frogobox.recycler.core.IFrogoViewAdapter
import com.frogobox.ui.R
import com.frogobox.ui.databinding.FrogoRvListType1Binding

class KotlinNoAdapterActivity : BaseActivity<ActivityBaseBinding>() {

    private val dataInjectorRClass = Constant.dummyData("Injector R class")
    private val dataInjectorBinding = Constant.dummyData("Injector Binding")
    private val dataBuilderRClass = Constant.dummyData("Builder R class")
    private val dataBuilderBinding = Constant.dummyData("Builder Binding")

    override fun setupViewBinding(): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Kotlin No Adapter")
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnBinding.setOnClickListener {
                setupRvInjectorBinding()
            }
            btnR.setOnClickListener {
                setupRvInjector()
            }
            btnRBuilder.setOnClickListener {
                setupRvBuilder()
            }
            btnBindingBuilder.setOnClickListener {
                setupRvBuilderBinding()
            }
        }
        setupRvBuilder()
    }


    private fun setupRvInjector() {

        val adapterCallback = object :
            IFrogoViewAdapter<ExampleModel> {
            override fun setupInitComponent(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // Init component content item recyclerview
                view.findViewById<TextView>(R.id.frogo_rv_list_type_1_tv_title).text = data.name
            }

            override fun onItemClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }

            override fun onItemLongClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item long clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }
        }

        binding.frogoRecyclerView.injector<ExampleModel>()
            .addData(dataInjectorRClass)
            .addCustomView(R.layout.frogo_rv_list_type_1)
            .addEmptyView(null)
            .addCallback(adapterCallback)
            .createLayoutLinearVertical(false)
            .build()
    }

    private fun setupRvInjectorBinding() {

        val adapterCallback = object : IFrogoBindingAdapter<ExampleModel, FrogoRvListType1Binding> {
            override fun setupInitComponent(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                binding.frogoRvListType1TvTitle.text = data.name
            }

            override fun setViewBinding(parent: ViewGroup): FrogoRvListType1Binding {
                return FrogoRvListType1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun onItemClicked(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
                notifyListener.frogoNotifyItemRemoved(data)
            }

            override fun onItemLongClicked(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item long clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }
        }

        binding.frogoRecyclerView.injectorBinding<ExampleModel, FrogoRvListType1Binding>()
            .addData(dataInjectorBinding)
            .addCallback(adapterCallback)
            .createLayoutLinearVertical(false)
            .build()
    }

    private fun setupRvBuilder() {
        binding.frogoRecyclerView.builder(object : IFrogoBuilderRv<ExampleModel> {
            override fun setupData(): List<ExampleModel> {
                // Setup data FrogoRecyclerView
                return dataBuilderRClass
            }

            override fun setupCustomView(): Int {
                // Setup Custom View
                return R.layout.frogo_rv_list_type_1
            }

            override fun setupEmptyView(): Int? {
                // Setup Empty View
                return null
            }

            override fun setupLayoutManager(context: Context): RecyclerView.LayoutManager {
                // Setup Layout Manager of FrogoRecyclerView
                return FrogoLayoutManager.linearLayoutVertical(context)
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun setupInitComponent(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // Init component content item recyclerview
                view.findViewById<TextView>(R.id.frogo_rv_list_type_1_tv_title).text = data.name
            }

            override fun onItemClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }

            override fun onItemLongClicked(
                view: View,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item long clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }
        })
    }

    private fun setupRvBuilderBinding() {
        binding.frogoRecyclerView.builderBinding(object :
            IFrogoBuilderRvBinding<ExampleModel, FrogoRvListType1Binding> {
            override fun setupData(): List<ExampleModel> {
                // Setup data FrogoRecyclerView
                return dataBuilderBinding
            }

            override fun setupLayoutManager(context: Context): RecyclerView.LayoutManager {
                // Setup Layout Manager of FrogoRecyclerView
                return FrogoLayoutManager.linearLayoutVertical(context)
            }

            override fun setupInitComponent(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                binding.frogoRvListType1TvTitle.text = data.name
            }

            override fun setViewBinding(parent: ViewGroup): FrogoRvListType1Binding {
                return FrogoRvListType1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun onItemClicked(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }

            override fun onItemLongClicked(
                binding: FrogoRvListType1Binding,
                data: ExampleModel,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<ExampleModel>
            ) {
                // setup item long clicked on frogo recycler view
                FLog.d("Clicked on Position : $position")
                showToast(data.name)
            }

        })
    }

}
