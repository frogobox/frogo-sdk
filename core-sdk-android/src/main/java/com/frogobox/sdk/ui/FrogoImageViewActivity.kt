package com.frogobox.sdk.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.frogobox.sdk.databinding.ActivityFrogoImageViewBinding
import com.frogobox.sdk.view.FrogoBindActivity

class FrogoImageViewActivity : FrogoBindActivity<ActivityFrogoImageViewBinding>(){

    companion object{
        const val IMAGE_VIEW_ID = "image_view_id"
        const val IMAGE_BITMAP = "image_bitmap"
        const val IMAGE_DRAWABLE = "image_drawable"
        const val IMAGE_URI = "image_uri"
        const val IMAGE_URL = "image_url"
    }

    override fun setupViewBinding(): ActivityFrogoImageViewBinding {
        return ActivityFrogoImageViewBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        if (intent.hasExtra(IMAGE_URI)) {
            val uri = intent.getStringExtra(IMAGE_URI).toString()

            Glide.with(this)
                .asBitmap()
                .load(uri)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.indicator.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        model: Any,
                        target: Target<Bitmap>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.ivImage.setImageBitmap(resource)
                        binding.indicator.visibility = View.GONE
                        return true
                    }
                })
                .into(binding.ivImage)
        }
    }

}