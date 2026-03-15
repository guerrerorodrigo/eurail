package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.ui.mvi.Reducer
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject

internal class MainReducer @Inject constructor() : Reducer<MainState, MainAction> {
    override fun reduce(
        state: MainState,
        action: MainAction,
    ): MainState = when (action) {
        is MainAction.OnArticlesLoaded -> state.copy(
            articles = action.articles,
            visibleArticles = action.articles.withIndex().toPersistentList(),
            isLoading = false,
            searchQuery = "",
            fullScreenMessageState = null,
        )
        is MainAction.OnSearchQueryChanged -> state.copy(
            searchQuery = action.query,
        )
        is MainAction.OnSearchPerformed -> state.copy(
            visibleArticles = action.articles.withIndex().toPersistentList(),
        )
        is MainAction.OnNetworkStateChanged -> state.copy(
            isNetworkAvailable = action.isAvailable,
        )
        is MainAction.OnShowFullScreenMessage -> state.copy(
            fullScreenMessageState = action.fullScreenMessageState,
            isLoading = false,
        )
        MainAction.OnRetry -> state.copy(
            fullScreenMessageState = null,
            isLoading = true,
        )
        MainAction.OnPause,
        MainAction.OnResume,
        -> state
    }
}
