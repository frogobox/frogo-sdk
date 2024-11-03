package com.frogobox.appuikit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.frogobox.R
import com.frogobox.appuikit.animation.SampleFrogoAnimationActivity
import com.frogobox.appuikit.compose.ComposeActivity
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.appuikit.loadingindicator.SampleFrogoLoadingIndicatorViewActivity
import com.frogobox.appuikit.model.Main
import com.frogobox.appuikit.recycler.RecyclerViewActivity
import com.frogobox.databinding.ActivityMainFrogoUiBinding
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoViewAdapter

class MainUIActivity : BaseActivity<ActivityMainFrogoUiBinding>() {

    override fun setupViewBinding(): ActivityMainFrogoUiBinding {
        return ActivityMainFrogoUiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    private fun data(): MutableList<Main> {
        val data = mutableListOf<Main>()
        data.add(Main("RecyclerView Layout", Intent(this, RecyclerViewActivity::class.java)))
        data.add(Main("Compose (Experimental)", Intent(this, ComposeActivity::class.java)))
        data.add(Main("Frogo Animation", Intent(this, SampleFrogoAnimationActivity::class.java)))
        data.add(
            Main(
                "Frogo Loading Indicator",
                Intent(this, SampleFrogoLoadingIndicatorViewActivity::class.java)
            )
        )
        return data
    }

    private fun setupRecyclerView() {
        binding.frogoRv.injector<Main>()
            .addCustomView(R.layout.frogo_rv_list_type_1)
            .addData(data())
            .addCallback(object : IFrogoViewAdapter<Main> {
                override fun onItemClicked(
                    view: View,
                    data: Main,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Main>,
                ) {
                    startActivity(data.intent)
                }

                override fun onItemLongClicked(
                    view: View,
                    data: Main,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Main>,
                ) {
                }

                override fun setupInitComponent(
                    view: View,
                    data: Main,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Main>,
                ) {
                    view.findViewById<TextView>(R.id.frogo_rv_list_type_1_tv_title).text = data.name
                }

                override fun areContentsTheSame(oldItem: Main, newItem: Main): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(oldItem: Main, newItem: Main): Boolean {
                    return oldItem == newItem
                }
            })
            .createLayoutLinearVertical(false)
            .build()
    }

}