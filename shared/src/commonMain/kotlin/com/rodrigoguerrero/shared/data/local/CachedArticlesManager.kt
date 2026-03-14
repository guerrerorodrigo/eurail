package com.rodrigoguerrero.shared.data.local

import com.rodrigoguerrero.shared.data.local.dao.ArticleDao
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity

object CachedArticlesManager {
    suspend fun getValidCachedArticles(
        dao: ArticleDao,
    ): List<ArticleEntity> {
        val articles = dao.getArticles()
        return if (articles.isNotEmpty() && articles.all { CachePolicy.isValid(it.createdAt) }) {
            articles
        } else {
            emptyList()
        }
    }

    suspend fun hasValidArticlesCache(dao: ArticleDao): Boolean {
        val articles = dao.getArticles()
        return articles.isNotEmpty() && articles.all { CachePolicy.isValid(it.createdAt) }
    }
}
