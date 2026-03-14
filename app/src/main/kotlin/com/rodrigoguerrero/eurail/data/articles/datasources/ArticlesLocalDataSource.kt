package com.rodrigoguerrero.eurail.data.articles.datasources

import com.rodrigoguerrero.eurail.data.remote.models.ArticlesDto
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity

interface ArticlesLocalDataSource {
    suspend fun saveArticles(articlesDto: ArticlesDto)
    suspend fun getCachedArticles(): List<ArticleEntity>
    suspend fun hasValidCache(): Boolean
}
