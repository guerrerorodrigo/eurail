package com.rodrigoguerrero.eurail.domain.articles.mappers

import com.rodrigoguerrero.eurail.domain.articles.models.Article
import com.rodrigoguerrero.eurail.domain.articles.models.ArticleDetails
import com.rodrigoguerrero.eurail.data.articles.models.Article as DataArticle
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails as DataArticleDetails

internal fun DataArticle.toArticle() = Article(
    id = id,
    title = title,
    summary = summary,
    updatedDate = updatedDate,
)

internal fun DataArticleDetails.toArticleDetails() = ArticleDetails(
    id = id,
    title = title,
    content = content,
    updatedDate = updatedDate,
)
