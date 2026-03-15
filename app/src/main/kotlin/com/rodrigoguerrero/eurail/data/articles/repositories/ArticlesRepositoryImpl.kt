package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesLocalDataSource
import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesRemoteDataSource
import com.rodrigoguerrero.eurail.data.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.data.articles.mappers.toArticleDetails
import com.rodrigoguerrero.eurail.data.articles.mappers.toArticleDetailsEntity
import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails
import com.rodrigoguerrero.eurail.utils.network.NetworkMonitor
import com.rodrigoguerrero.eurail.utils.network.NoNetworkException
import javax.inject.Inject

internal class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource,
    private val localDataSource: ArticlesLocalDataSource,
    private val networkMonitor: NetworkMonitor,
) : ArticlesRepository {
    override suspend fun getArticles(): Result<List<Article>> {
        val cachedArticles = localDataSource
            .getCachedArticles()
            .map { it.toArticle() }

        if (cachedArticles.isNotEmpty()) {
            return Result.success(cachedArticles)
        } else {
            localDataSource.deleteAllArticles()
        }

        // Simulating no network response from mock server
        if (!networkMonitor.isConnected()) {
            return Result.failure(NoNetworkException())
        }

        return getRemoteArticles()
    }

    override suspend fun getRemoteArticles() = remoteDataSource
        .getArticles()
        .mapCatching { articlesDto ->
            localDataSource.saveArticles(articlesDto)
            articlesDto.articles.map { article -> article.toArticle() }
        }

    override suspend fun getArticleDetails(id: Int): Result<ArticleDetails> {
        val cachedArticle = localDataSource
            .getCachedArticleDetails(id = id)
            ?.toArticleDetails()

        if (cachedArticle != null) {
            return Result.success(cachedArticle)
        }

        return remoteDataSource
            .getArticleDetails(id = id)
            .mapCatching { detailsDto ->
                localDataSource.saveArticleDetails(detailsDto.toArticleDetailsEntity())
                detailsDto.toArticleDetails()
            }
    }
}
