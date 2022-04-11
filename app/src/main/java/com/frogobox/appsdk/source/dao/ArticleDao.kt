package com.frogobox.appsdk.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frogobox.appsdk.model.Article
import io.reactivex.rxjava3.core.Single

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
@Dao
interface ArticleDao {

    @Query("SELECT * FROM article WHERE id = :id")
    fun getArticleById(id: Int): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticles(articles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAllArticles()

    @Query("SELECT * FROM article")
    fun getArticleList(): Single<List<Article>>

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>

}