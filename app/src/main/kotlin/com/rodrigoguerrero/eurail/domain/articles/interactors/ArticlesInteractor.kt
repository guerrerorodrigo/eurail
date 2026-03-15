package com.rodrigoguerrero.eurail.domain.articles.interactors

import com.rodrigoguerrero.eurail.domain.articles.models.Article
import com.rodrigoguerrero.eurail.domain.articles.models.ArticleDetails

interface ArticlesInteractor {
    suspend fun loadArticles(): Result<List<Article>>
    suspend fun loadArticleDetails(id: Int): Result<ArticleDetails>
}
