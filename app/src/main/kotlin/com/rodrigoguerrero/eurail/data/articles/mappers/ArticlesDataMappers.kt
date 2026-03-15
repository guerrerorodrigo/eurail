package com.rodrigoguerrero.eurail.data.articles.mappers

import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails
import com.rodrigoguerrero.eurail.data.remote.models.ArticleDetailsDto
import com.rodrigoguerrero.eurail.data.remote.models.ArticleDto
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal fun ArticleDto.toArticle() = Article(
    id = id,
    title = title,
    updatedDate = updatedDate,
    summary =  summary,
)

@OptIn(ExperimentalTime::class)
internal fun ArticleDto.toArticleEntity() = ArticleEntity(
    id = id,
    title = title,
    updatedDate = updatedDate,
    summary = summary,
    createdAt = Clock.System.now().toEpochMilliseconds(),
)

internal fun ArticleEntity.toArticle() = Article(
    id = id,
    title = title,
    updatedDate = updatedDate,
    summary =  summary,
)

internal fun ArticleDetailsDto.toArticleDetails() = ArticleDetails(
    id = id,
    title = title,
    content = content,
    updatedDate = updatedDate,
)
