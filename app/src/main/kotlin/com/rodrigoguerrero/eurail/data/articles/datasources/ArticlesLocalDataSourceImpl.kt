package com.rodrigoguerrero.eurail.data.articles.datasources

import com.rodrigoguerrero.eurail.data.articles.mappers.toArticleEntity
import com.rodrigoguerrero.eurail.data.remote.models.ArticlesDto
import com.rodrigoguerrero.shared.data.local.AppDatabase
import com.rodrigoguerrero.shared.data.local.CachedArticlesManager
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity
import javax.inject.Inject

internal class ArticlesLocalDataSourceImpl @Inject constructor(
    private val database: AppDatabase,
) : ArticlesLocalDataSource {
    override suspend fun saveArticles(articlesDto: ArticlesDto) {
        val articleEntities = articlesDto.articles.map { it.toArticleEntity() }
        database.articlesDao().insert(articleEntities)
    }

    override suspend fun getCachedArticles(): List<ArticleEntity> {
        val cachedArticles = CachedArticlesManager
            .getValidCachedArticles(dao = database.articlesDao())
        return cachedArticles
    }

    override suspend fun hasValidArticlesCache(): Boolean {
        return CachedArticlesManager.hasValidArticlesCache(database.articlesDao())
    }
}
