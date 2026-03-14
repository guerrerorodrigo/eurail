package com.rodrigoguerrero.eurail.data.articles.mappers

import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.remote.models.ArticleDto

internal fun ArticleDto.toArticle() = Article(
    id = id,
    title = title,
    updatedDate = updatedDate,
    summary =  summary,
)
