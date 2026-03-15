package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails

interface ArticlesRepository {
    suspend fun getArticles(): Result<List<Article>>
    suspend fun getRemoteArticles(): Result<List<Article>>
    suspend fun getArticleDetails(id: Int): Result<ArticleDetails>
}
