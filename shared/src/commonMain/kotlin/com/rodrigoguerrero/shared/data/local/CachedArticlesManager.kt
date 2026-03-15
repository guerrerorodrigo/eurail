package com.rodrigoguerrero.shared.data.local

import com.rodrigoguerrero.shared.data.local.dao.ArticleDao
import com.rodrigoguerrero.shared.data.local.entities.ArticleAndDetails
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object CachedArticlesManager {
    private val cachePolicy = CachePolicy()
    suspend fun getValidCachedArticles(
        dao: ArticleDao,
    ): List<ArticleEntity> {
        val articles = dao.getArticles()
        return if (articles.isNotEmpty() && articles.all { cachePolicy.isValid(it.createdAt) }) {
            articles
        } else {
            emptyList()
        }
    }

    suspend fun getValidCachedArticleDetails(
        id: Int,
        dao: ArticleDao,
    ): ArticleAndDetails? {
        val article = dao.getArticleDetails(id = id)
        return if (article?.details != null && cachePolicy.isValid(article.details.createdAt)) {
            article
        } else {
            null
        }
    }
}
