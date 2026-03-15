package com.rodrigoguerrero.shared.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigoguerrero.shared.data.local.entities.ArticleAndDetails
import com.rodrigoguerrero.shared.data.local.entities.ArticleDetailsEntity
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntities: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(detailsEntity: ArticleDetailsEntity)

    @Query("DELETE FROM article_details")
    suspend fun deleteAllArticleDetails()

    @Query("SELECT * FROM articles WHERE id=:id")
    suspend fun getArticleDetails(id: Int): ArticleAndDetails?
}
