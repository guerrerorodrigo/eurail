package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesRemoteDataSource
import com.rodrigoguerrero.eurail.data.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.data.articles.models.Article
import javax.inject.Inject

internal class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource,
) : ArticlesRepository {
    override suspend fun getArticles(): Result<List<Article>> {
        return remoteDataSource
            .getArticles()
            .mapCatching { articlesDto ->
                articlesDto.articles.map { article -> article.toArticle() }
            }
    }
}
