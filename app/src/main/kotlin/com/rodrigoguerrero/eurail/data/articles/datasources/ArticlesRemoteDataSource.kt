package com.rodrigoguerrero.eurail.data.articles.datasources

import com.rodrigoguerrero.eurail.data.remote.models.ArticleDetailsDto
import com.rodrigoguerrero.eurail.data.remote.models.ArticlesDto

interface ArticlesRemoteDataSource {
    suspend fun getArticles(): Result<ArticlesDto>

    suspend fun getArticleDetails(id: Int): Result<ArticleDetailsDto>
}
