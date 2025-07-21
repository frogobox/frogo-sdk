package com.frogobox.appuikit.recycler

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.frogobox.R
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoViewAdapter

/*
 * Created by faisalamir on 22/05/21
 * FrogoUIKit
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
object FrogoRvAdapter {

    fun frogoRvAdaper(nameItemRv: String, context: Context): IFrogoViewAdapter<String> {

        return when (nameItemRv) {

            "frogo_rv_list_type_1" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_1_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_2" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_2_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_2_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_3" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_3_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_3_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_3_tv_desc).text =
                            context.getString(
                                R.string.frogo_dummy_desc
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_4" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_4_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_4_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_5" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_5_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_5_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_5_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_6" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_6_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_6_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_6_tv_desc).text =
                            context.getString(
                                R.string.frogo_dummy_desc
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_6_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_7" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_7_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_7_civ_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_8" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_8_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_8_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_8_civ_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_9" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_9_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_9_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_10" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_10_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_10_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_10_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_11" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_11_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_11_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_11_tv_desc).text =
                            context.getString(
                                R.string.frogo_dummy_desc
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_list_type_11_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_list_type_12" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_list_type_12_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_1" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_1_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_1_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_2" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_2_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_2_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_2_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_3" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_desc).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_3_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_4" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_4_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_4_civ_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_5" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_5_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_5_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_5_civ_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_6" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_6_tv_title).text =
                            context.getString(
                                R.string.frogo_dummy_title
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_6_tv_subtitle).text =
                            context.getString(
                                R.string.frogo_dummy_subtitle
                            )
                        view.findViewById<TextView>(R.id.frogo_rv_grid_type_6_tv_desc).text =
                            context.getString(
                                R.string.frogo_dummy_desc
                            )
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_6_civ_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            "frogo_rv_grid_type_7" -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                        view.findViewById<ImageView>(R.id.frogo_rv_grid_type_7_iv_poster)
                            .setImageResource(
                                R.drawable.ic_artist
                            )
                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }

            else -> {
                object : IFrogoViewAdapter<String> {
                    override fun onItemClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun onItemLongClicked(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {
                    }

                    override fun setupInitComponent(
                        view: View,
                        data: String,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<String>,
                    ) {

                    }

                    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }

                    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                        return oldItem == newItem
                    }
                }
            }
        }
    }
}