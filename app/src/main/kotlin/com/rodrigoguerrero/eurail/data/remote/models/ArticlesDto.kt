package com.rodrigoguerrero.eurail.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesDto(
    val articles: List<ArticleDto>,
)
