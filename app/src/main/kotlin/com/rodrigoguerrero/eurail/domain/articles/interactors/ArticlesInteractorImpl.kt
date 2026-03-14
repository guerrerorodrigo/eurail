package com.rodrigoguerrero.eurail.domain.articles.interactors

import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepository
import com.rodrigoguerrero.eurail.domain.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.domain.articles.models.Article
import com.rodrigoguerrero.eurail.utils.network.NetworkMonitor
import javax.inject.Inject

internal class ArticlesInteractorImpl @Inject constructor(
    private val repository: ArticlesRepository,
    private val networkMonitor: NetworkMonitor,
) : ArticlesInteractor {
    override suspend fun loadArticles(): Result<List<Article>> {
        // TODO: here we should check for the result as failure, but since it is a mocked server,
        // it always returns a value.
        return if (!networkMonitor.isConnected()) {
            loadArticlesFromCache()
        } else {
            loadArticlesFromNetwork()
        }
    }

    override suspend fun refreshCache() {
        if (!repository.hasValidCache()) {
            loadArticlesFromNetwork()
        }
    }

    private suspend fun loadArticlesFromCache(): Result<List<Article>> {
        return repository.getCachedArticles()
            .mapCatching { articles -> articles.map { article -> article.toArticle() } }
    }

    private suspend fun loadArticlesFromNetwork(): Result<List<Article>> {
        return repository.getArticles()
            .mapCatching { articles -> articles.map { article -> article.toArticle() } }
    }
}
