package com.frogobox.appsdk.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * NewsApi
 * Copyright (C) 15/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.news.data.model
 *
 */
@Entity(tableName = "article")
data class Article(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    var author: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    var content: String? = null
)