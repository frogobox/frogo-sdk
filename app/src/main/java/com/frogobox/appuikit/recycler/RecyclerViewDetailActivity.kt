package com.frogobox.appuikit.recycler

import android.os.Bundle
import androidx.core.view.updatePadding
import com.frogobox.appuikit.recycler.FrogoRvConstant.TYPE_GRID
import com.frogobox.appuikit.recycler.FrogoRvConstant.dummyData
import com.frogobox.appuikit.recycler.FrogoRvAdapter.frogoRvAdaper
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.databinding.ActivityRecyclerViewDetailBinding
import com.frogobox.appuikit.model.Layout
import com.frogobox.recycler.core.IFrogoViewAdapter
import com.google.gson.Gson

class RecyclerViewDetailActivity : BaseActivity<ActivityRecyclerViewDetailBinding>() {

    override fun setupViewBinding(): ActivityRecyclerViewDetailBinding {
        return ActivityRecyclerViewDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity(extraData().name)
        setupRecyclerView(frogoRv())
    }

    private fun extraData() : Layout {
        val extra = intent.getStringExtra("EXTRA_DATA")
        return Gson().fromJson(extra, Layout::class.java)
    }

    private fun frogoRv() : IFrogoViewAdapter<String>{
        return frogoRvAdaper(extraData().name, this)
    }
    
    private fun setupRecyclerView(adapter: IFrogoViewAdapter<String>) {

        val scale = resources.displayMetrics.density
        val dpAsPixels = (16 * scale + 0.5f)

        if (extraData().type == TYPE_GRID) {
            binding.frogoRv.apply {
                injector<String>()
                    .addCustomView(extraData().layout)
                    .addCallback(adapter)
                    .addData(dummyData())
                    .createLayoutGrid(2)
                    .build()

                updatePadding(left = dpAsPixels.toInt())
            }
        } else {
            binding.frogoRv.injector<String>()
                .addCustomView(extraData().layout)
                .addCallback(adapter)
                .addData(dummyData())
                .createLayoutLinearVertical(false)
                .build()
        }

    }

}