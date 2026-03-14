package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.models.Article

interface ArticlesRepository {
    suspend fun getArticles(): Result<List<Article>>
    suspend fun hasValidCache(): Boolean
    suspend fun getRemoteArticles(): Result<List<Article>>
}
