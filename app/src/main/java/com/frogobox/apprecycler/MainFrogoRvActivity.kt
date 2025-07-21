package com.frogobox.apprecycler

import android.content.Intent
import android.os.Bundle
import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.apprecycler.sample.issue.AnswerIssueActivity
import com.frogobox.apprecycler.sample.java.noadapter.multiview.JavaNoAdapterMultiViewActivity
import com.frogobox.apprecycler.sample.java.noadapter.simple.JavaNoAdapterActivity
import com.frogobox.apprecycler.sample.java.usingadapter.JavaSampleActivity
import com.frogobox.apprecycler.sample.kotlin.noadapter.multiview.KotlinNoAdapterMultiVewActivity
import com.frogobox.apprecycler.sample.kotlin.noadapter.progress.KotlinProgressActivity
import com.frogobox.apprecycler.sample.kotlin.noadapter.shimmer.KotlinShimmerActivity
import com.frogobox.apprecycler.sample.kotlin.noadapter.simple.KotlinNoAdapterActivity
import com.frogobox.apprecycler.sample.kotlin.usingadapter.nested.KotlinNestedActivity
import com.frogobox.apprecycler.sample.kotlin.usingadapter.nested.KotlinSimpleNestedActivity
import com.frogobox.apprecycler.sample.kotlin.usingadapter.simple.KotlinSampleActivity
import com.frogobox.databinding.ActivityMainFrogoRvBinding

class MainFrogoRvActivity : BaseActivity<ActivityMainFrogoRvBinding>() {

    override fun setupViewBinding(): ActivityMainFrogoRvBinding {
        return ActivityMainFrogoRvBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupButton()
    }

    private fun setupButton() {

        binding.apply {
            btnWithData.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinSampleActivity::class.java))
            }

            btnEmptyData.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, JavaSampleActivity::class.java))
            }

            btnJavaNoAdapter.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, JavaNoAdapterActivity::class.java))
            }

            btnKotlinNoAdapter.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinNoAdapterActivity::class.java))
            }

            btnKotlinMultiview.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainFrogoRvActivity,
                        KotlinNoAdapterMultiVewActivity::class.java
                    )
                )
            }

            btnJavaMultiview.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, JavaNoAdapterMultiViewActivity::class.java))
            }

            btnKotlinShimmer.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinShimmerActivity::class.java))
            }

            btnKotlinProgress.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinProgressActivity::class.java))
            }

            btnNestedSimple.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinSimpleNestedActivity::class.java))
            }

            btnNested.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, KotlinNestedActivity::class.java))
            }

            btnAnswerIssue1.setOnClickListener {
                startActivity(Intent(this@MainFrogoRvActivity, AnswerIssueActivity::class.java))
            }
        }

    }

}
