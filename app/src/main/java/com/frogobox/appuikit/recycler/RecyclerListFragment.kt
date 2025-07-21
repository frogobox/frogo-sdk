package com.frogobox.appuikit.recycler

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frogobox.R
import com.frogobox.appuikit.core.BaseFragment
import com.frogobox.appuikit.model.Layout
import com.frogobox.databinding.FragmentRecyclerListBinding
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoViewAdapter
import com.google.gson.Gson


class RecyclerListFragment : BaseFragment<FragmentRecyclerListBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRecyclerListBinding {
        return FragmentRecyclerListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun intentToLayoutSample(data: Layout) {
        val dataString = Gson().toJson(data)
        val intent = Intent(mActivity, RecyclerViewDetailActivity::class.java)
        intent.putExtra("EXTRA_DATA", dataString)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        binding.frogoRv.injector<Layout>()
            .addCustomView(R.layout.frogo_rv_list_type_1)
            .addData(FrogoRvConstant.dataRvList())
            .addCallback(object : IFrogoViewAdapter<Layout> {
                override fun onItemClicked(
                    view: View,
                    data: Layout,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Layout>,
                ) {
                    intentToLayoutSample(data)
                }

                override fun onItemLongClicked(
                    view: View,
                    data: Layout,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Layout>,
                ) {
                }

                override fun setupInitComponent(
                    view: View,
                    data: Layout,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<Layout>,
                ) {
                    view.findViewById<TextView>(R.id.frogo_rv_list_type_1_tv_title).text = data.name
                }

                override fun areContentsTheSame(oldItem: Layout, newItem: Layout): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(oldItem: Layout, newItem: Layout): Boolean {
                    return oldItem == newItem
                }
            })
            .createLayoutLinearVertical(false)
            .build()
    }


}