package com.rodrigoguerrero.eurail.ui.details

import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessageState
import com.rodrigoguerrero.eurail.ui.mvi.MviAction

internal sealed interface DetailsAction : MviAction {
    data class OnLoad(val id: Int?) : DetailsAction
    data class OnRetry(val id: Int) : DetailsAction
    data class OnShowFullScreenMessage(val fullScreenMessageState: FullScreenMessageState): DetailsAction
    data class OnArticleLoaded(
        val id: Int,
        val title: String,
        val content: String,
        val updatedDate: String,
    ) : DetailsAction
}
