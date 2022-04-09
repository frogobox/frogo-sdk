package com.frogobox.appsdk.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding>() {

    override fun setupViewBinding(): ActivityNewsDetailBinding {
        return ActivityNewsDetailBinding.inflate(layoutInflater)
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {

    }
}