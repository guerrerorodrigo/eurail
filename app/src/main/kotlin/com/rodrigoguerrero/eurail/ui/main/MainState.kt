package com.rodrigoguerrero.eurail.ui.main

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessageState
import com.rodrigoguerrero.eurail.ui.main.components.ArticleCardState
import com.rodrigoguerrero.eurail.ui.mvi.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class MainState(
    val articles: ImmutableList<ArticleCardState>,
    val searchQuery: String,
    val isLoading: Boolean,
    val visibleArticles: ImmutableList<ArticleCardState>,
    val isNetworkAvailable: Boolean,
    val fullScreenMessageState: FullScreenMessageState?,
): State {
    companion object {
        val initial = MainState(
            articles = persistentListOf(),
            visibleArticles = persistentListOf(),
            searchQuery = "",
            isLoading = true,
            isNetworkAvailable = true,
            fullScreenMessageState = null,
        )
    }
}
