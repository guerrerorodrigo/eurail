package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesLocalDataSource
import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesRemoteDataSource
import com.rodrigoguerrero.eurail.data.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.data.articles.models.Article
import javax.inject.Inject

internal class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource,
    private val localDataSource: ArticlesLocalDataSource,
) : ArticlesRepository {
    override suspend fun getArticles(): Result<List<Article>> {
        return remoteDataSource
            .getArticles()
            .mapCatching { articlesDto ->
                localDataSource.saveArticles(articlesDto)
                articlesDto.articles.map { article -> article.toArticle() }
            }
    }

    override suspend fun getCachedArticles(): Result<List<Article>> {
        val cachedArticles = localDataSource
            .getCachedArticles()
            .map { it.toArticle() }

        return if (cachedArticles.isNotEmpty()) {
            Result.success(cachedArticles)
        } else {
            Result.failure(Exception("No cached articles"))
        }
    }

    override suspend fun hasValidCache(): Boolean {
        return localDataSource.hasValidCache()
    }
}
