package com.rodrigoguerrero.eurail.data.articles.mappers

import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails
import com.rodrigoguerrero.eurail.data.remote.models.ArticleDetailsDto
import com.rodrigoguerrero.eurail.data.remote.models.ArticleDto
import com.rodrigoguerrero.shared.data.local.entities.ArticleAndDetails
import com.rodrigoguerrero.shared.data.local.entities.ArticleDetailsEntity
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

@OptIn(ExperimentalTime::class)
internal fun ArticleDetailsDto.toArticleDetailsEntity() = ArticleDetailsEntity(
    id = 0,
    articleId = id,
    content = content,
    createdAt = Clock.System.now().toEpochMilliseconds(),
)

internal fun ArticleAndDetails.toArticleDetails() = ArticleDetails(
    id = article.id,
    title = article.title,
    content = details?.content.orEmpty(),
    updatedDate = article.updatedDate,
)
