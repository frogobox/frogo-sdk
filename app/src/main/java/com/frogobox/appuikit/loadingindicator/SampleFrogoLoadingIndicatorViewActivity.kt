package com.frogobox.appuikit.loadingindicator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.appuikit.core.BaseActivity
import com.frogobox.databinding.ActivitySampleLoadingIndicatorViewBinding
import com.frogobox.databinding.ItemIndicatorBinding

/**
 * Created by Jack Wang on 2016/8/5.
 */
class SampleFrogoLoadingIndicatorViewActivity :
    BaseActivity<ActivitySampleLoadingIndicatorViewBinding>() {

    private val dataIndicators = arrayOf(
        "BallPulseIndicator",
        "BallGridPulseIndicator",
        "BallClipRotateIndicator",
        "BallClipRotatePulseIndicator",
        "SquareSpinIndicator",
        "BallClipRotateMultipleIndicator",
        "BallPulseRiseIndicator",
        "BallRotateIndicator",
        "CubeTransitionIndicator",
        "BallZigZagIndicator",
        "BallZigZagDeflectIndicator",
        "BallTrianglePathIndicator",
        "BallScaleIndicator",
        "LineScaleIndicator",
        "LineScalePartyIndicator",
        "BallScaleMultipleIndicator",
        "BallPulseSyncIndicator",
        "BallBeatIndicator",
        "LineScalePulseOutIndicator",
        "LineScalePulseOutRapidIndicator",
        "BallScaleRippleIndicator",
        "BallScaleRippleMultipleIndicator",
        "BallSpinFadeLoaderIndicator",
        "LineSpinFadeLoaderIndicator",
        "TriangleSkewSpinIndicator",
        "PacmanIndicator",
        "BallGridBeatIndicator",
        "SemiCircleSpinIndicator"
    )

    override fun setupViewBinding(): ActivitySampleLoadingIndicatorViewBinding {
        return ActivitySampleLoadingIndicatorViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDetailActivity("Frogo Loading Indicator View")

        binding.recycler.apply {
            layoutManager = GridLayoutManager(this@SampleFrogoLoadingIndicatorViewActivity, 4)
            adapter = object : RecyclerView.Adapter<IndicatorHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int,
                ): IndicatorHolder {
                    return IndicatorHolder(
                        ItemIndicatorBinding.inflate(
                            LayoutInflater.from(
                                parent.context
                            ), parent, false
                        )
                    )
                }

                override fun onBindViewHolder(
                    holder: IndicatorHolder,
                    @SuppressLint("RecyclerView") position: Int,
                ) {
                    holder.setData(dataIndicators[position])
                }

                override fun getItemCount(): Int {
                    return dataIndicators.size
                }
            }
        }

    }

    internal class IndicatorHolder(private val binding: ItemIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: String) {
            binding.apply {
                indicator.setIndicator(data)
                itemLayout.setOnClickListener {
                    val intent =
                        Intent(binding.root.context, DetailLoadingIndicatorActivity::class.java)
                    intent.putExtra("indicator", data)
                    binding.root.context.startActivity(intent)
                }
            }

        }

    }

}