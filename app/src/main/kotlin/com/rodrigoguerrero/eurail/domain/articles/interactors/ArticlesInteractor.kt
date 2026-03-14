package com.rodrigoguerrero.eurail.domain.articles.interactors

import com.rodrigoguerrero.eurail.domain.articles.models.Article

interface ArticlesInteractor {
    suspend fun loadArticles(): Result<List<Article>>
    suspend fun refreshCache()
}
