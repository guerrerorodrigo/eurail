package com.rodrigoguerrero.eurail.ui.details

import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractor
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessageState
import com.rodrigoguerrero.eurail.ui.common.components.createFullScreenMessageState
import com.rodrigoguerrero.eurail.ui.details.DetailsAction.OnShowFullScreenMessage
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailsMiddleware @Inject constructor(
    private val interactor: ArticlesInteractor,
) : Middleware<DetailsState, DetailsAction>() {
    override fun process(
        state: DetailsState,
        action: DetailsAction,
    ) {
        when (action) {
            is DetailsAction.OnLoad -> loadArticleDetails(action.id)
            is DetailsAction.OnRetry -> loadArticleDetails(action.id)
            else -> Unit
        }
    }

    private fun loadArticleDetails(id: Int?) {
        if (id == null) {
            dispatch(
                OnShowFullScreenMessage(
                    FullScreenMessageState.LocalFullScreenMessage(
                        messageRes = R.string.details_error,
                        ctaLabelRes = R.string.try_again,
                    ),
                ),
            )
            return
        }

        scope.launch {
            interactor
                .loadArticleDetails(id = id)
                .fold(
                    onSuccess = { details ->
                        dispatch(
                            DetailsAction.OnArticleLoaded(
                                title = details.title,
                                id = details.id,
                                content = details.content,
                                updatedDate = details.updatedDate,
                            ),
                        )
                    },
                    onFailure = { throwable ->
                        dispatch(
                            OnShowFullScreenMessage(createFullScreenMessageState(throwable))
                        )
                    },
                )
        }
    }
}
