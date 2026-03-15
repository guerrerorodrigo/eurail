package com.rodrigoguerrero.eurail.data.articles.datasources

import com.rodrigoguerrero.eurail.data.remote.models.ArticlesDto
import com.rodrigoguerrero.shared.data.local.entities.ArticleAndDetails
import com.rodrigoguerrero.shared.data.local.entities.ArticleDetailsEntity
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity

interface ArticlesLocalDataSource {
    suspend fun saveArticles(articlesDto: ArticlesDto)
    suspend fun getCachedArticles(): List<ArticleEntity>
    suspend fun deleteAllArticles()
    suspend fun getCachedArticleDetails(id: Int): ArticleAndDetails?
    suspend fun saveArticleDetails(articlesDetailsEntity: ArticleDetailsEntity)
}
