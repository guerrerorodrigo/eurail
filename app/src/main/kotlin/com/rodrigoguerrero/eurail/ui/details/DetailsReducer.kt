package com.rodrigoguerrero.eurail.ui.details

import com.rodrigoguerrero.eurail.ui.mvi.Reducer
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() : Reducer<DetailsState, DetailsAction> {
    override fun reduce(
        state: DetailsState,
        action: DetailsAction,
    ): DetailsState = when (action) {
        is DetailsAction.OnArticleLoaded -> state.copy(
            title = action.title,
            content = action.content,
            id = action.id,
            updatedDate = action.updatedDate,
            isLoading = false,
        )
        is DetailsAction.OnRetry -> state.copy(
            isLoading = true,
            fullScreenMessageState = null,
        )
        is DetailsAction.OnShowFullScreenMessage -> state.copy(
            isLoading = false,
            fullScreenMessageState = action.fullScreenMessageState,
        )
        is DetailsAction.OnLoad -> state
    }
}
