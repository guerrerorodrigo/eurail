package com.rodrigoguerrero.eurail.ui.main.mappers

import com.rodrigoguerrero.eurail.ui.main.components.ArticleCardState
import kotlinx.collections.immutable.toPersistentList
import com.rodrigoguerrero.eurail.domain.articles.models.Article as DomainArticle

internal fun List<DomainArticle>.toArticleCardState() = map { article ->
    ArticleCardState(
        id = article.id,
        title = article.title,
        summary = article.summary,
        updatedDate = article.updatedDate,
    )
}.toPersistentList()
