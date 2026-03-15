package com.rodrigoguerrero.eurail.domain.articles.interactors

import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepository
import com.rodrigoguerrero.eurail.domain.articles.mappers.toArticle
import com.rodrigoguerrero.eurail.domain.articles.mappers.toArticleDetails
import com.rodrigoguerrero.eurail.domain.articles.models.Article
import com.rodrigoguerrero.eurail.domain.articles.models.ArticleDetails
import javax.inject.Inject

internal class ArticlesInteractorImpl @Inject constructor(
    private val repository: ArticlesRepository,
) : ArticlesInteractor {
    override suspend fun loadArticles(): Result<List<Article>> {
        return repository
            .getArticles()
            .mapCatching { articles -> articles.map { article -> article.toArticle() } }
    }

    override suspend fun loadArticleDetails(id: Int): Result<ArticleDetails> {
        return repository
            .getArticleDetails(id = id)
            .mapCatching { articleDetails -> articleDetails.toArticleDetails() }
    }
}
