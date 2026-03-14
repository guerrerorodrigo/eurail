package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.models.Article

interface ArticlesRepository {
    suspend fun getArticles(): Result<List<Article>>
}
