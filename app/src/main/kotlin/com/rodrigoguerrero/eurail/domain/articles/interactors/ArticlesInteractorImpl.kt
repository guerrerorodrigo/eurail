package com.rodrigoguerrero.eurail.domain.articles.interactors

import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepository
import com.rodrigoguerrero.eurail.domain.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.domain.articles.models.Article
import javax.inject.Inject

internal class ArticlesInteractorImpl @Inject constructor(
    private val repository: ArticlesRepository,
) : ArticlesInteractor {
    override suspend fun loadArticles(): Result<List<Article>> {
        val articlesResult = repository.getArticles()
        return articlesResult
            .mapCatching { articles -> articles.map { article -> article.toArticle() } }
    }
}
