package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.ui.mvi.Reducer
import javax.inject.Inject

internal class MainReducer @Inject constructor() : Reducer<MainState, MainAction> {
    override fun reduce(
        state: MainState,
        action: MainAction,
    ): MainState = when (action) {
        is MainAction.OnArticlesLoaded -> state.copy(
            articles = action.articles,
            visibleArticles = action.articles,
            isLoading = false,
            searchQuery = "",
            fullScreenMessageState = null,
        )
        is MainAction.OnSearchQueryChanged -> state.copy(
            searchQuery = action.query,
        )
        is MainAction.OnSearchPerformed -> state.copy(
            visibleArticles = action.articles,
        )
        is MainAction.OnNetworkStateChanged -> state.copy(
            isNetworkAvailable = action.isAvailable,
        )
        is MainAction.OnShowFullScreenMessage -> state.copy(
            fullScreenMessageState = action.fullScreenMessageState,
            isLoading = false,
        )
        MainAction.Load,
        MainAction.OnPause,
        MainAction.OnResume,
        -> state
    }
}
