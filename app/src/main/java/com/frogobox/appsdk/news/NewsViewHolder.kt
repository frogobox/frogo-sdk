package com.frogobox.appsdk.news

import androidx.recyclerview.widget.RecyclerView
import com.frogobox.appsdk.databinding.ItemNewsBinding
import com.frogobox.appsdk.model.Article
import com.frogobox.sdk.ext.glideLoad
import com.frogobox.sdk.ext.startActivityExt


/*
 * Created by faisalamir on 11/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Article) {
        binding.apply {
            ivNewsImage.glideLoad(data.urlToImage)
            tvNewsTitle.text = data.title
            tvNewsDescription.text = data.description
            root.setOnClickListener {
                it.context.startActivityExt<NewsDetailActivity, Article>(
                    "EXTRA_NEWS_DETAIL",
                    data
                )
            }
        }
    }

}