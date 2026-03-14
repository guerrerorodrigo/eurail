package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.runtime.Stable

@Stable
data class ArticleCardState(
    val id: Int,
    val title: String,
    val summary: String,
    val updatedDate: String,
)
