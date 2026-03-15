package com.rodrigoguerrero.eurail.ui.details

import androidx.compose.ui.text.AnnotatedString
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessageState
import com.rodrigoguerrero.eurail.ui.mvi.State

internal data class DetailsState(
    val id: Int,
    val title: String,
    val content: String,
    val updatedDate: String,
    val isLoading: Boolean,
    val fullScreenMessageState: FullScreenMessageState?,
) : State {
    companion object {
        val initial = DetailsState(
            id = 0,
            title = "",
            content = "",
            updatedDate = "",
            isLoading = true,
            fullScreenMessageState = null,
        )
    }
}
