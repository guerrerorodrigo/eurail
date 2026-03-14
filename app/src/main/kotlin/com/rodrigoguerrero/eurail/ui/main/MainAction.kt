package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.ui.main.components.ArticleCardState
import com.rodrigoguerrero.eurail.ui.mvi.MviAction
import kotlinx.collections.immutable.ImmutableList

sealed interface MainAction : MviAction {
    data object Load : MainAction
    data class OnArticlesLoaded(val articles: ImmutableList<ArticleCardState>) : MainAction
    data class OnSearchPerformed(val articles: ImmutableList<ArticleCardState>) : MainAction
    data class OnSearchQueryChanged(val query: String) : MainAction
    data object OnResume : MainAction
    data object OnPause : MainAction
    data class OnNetworkStateChanged(val isAvailable: Boolean): MainAction
}
