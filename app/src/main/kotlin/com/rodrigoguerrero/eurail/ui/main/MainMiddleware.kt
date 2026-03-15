package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractor
import com.rodrigoguerrero.eurail.domain.articles.models.Article
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessageState
import com.rodrigoguerrero.eurail.ui.common.components.createFullScreenMessageState
import com.rodrigoguerrero.eurail.ui.main.MainAction.OnShowFullScreenMessage
import com.rodrigoguerrero.eurail.ui.main.components.ArticleCardState
import com.rodrigoguerrero.eurail.ui.main.mappers.toArticleCardState
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainMiddleware @Inject constructor(
    private val articlesInteractor: ArticlesInteractor,
) : Middleware<MainState, MainAction>() {
    override fun process(
        state: MainState,
        action: MainAction,
    ) {
        when (action) {
            MainAction.OnRetry,
            MainAction.OnResume -> loadArticles()

            is MainAction.OnSearchQueryChanged -> filterArticles(
                query = action.query,
                articles = state.articles,
            )

            else -> Unit
        }
    }

    private fun loadArticles() {
        scope.launch {
            articlesInteractor
                .loadArticles()
                .fold(
                    onSuccess = { articles -> processSuccess(articles) },
                    onFailure = { throwable ->
                        dispatch(OnShowFullScreenMessage(createFullScreenMessageState(throwable)))
                    },
                )
        }
    }

    private fun processSuccess(articles: List<Article>) {
        if (articles.isEmpty()) {
            dispatch(
                OnShowFullScreenMessage(
                    FullScreenMessageState.LocalFullScreenMessage(
                        messageRes = R.string.empty_screen_message,
                        ctaLabelRes = R.string.reload,
                    ),
                ),
            )
        } else {
            dispatch(MainAction.OnArticlesLoaded(articles = articles.toArticleCardState()))
        }
    }

    private fun filterArticles(
        query: String,
        articles: ImmutableList<ArticleCardState>,
    ) {
        val visibleArticles = when (query) {
            "" -> articles
            else -> articles.filter {
                it.title.contains(query, ignoreCase = true) || it.summary.contains(
                    query,
                    ignoreCase = true,
                )
            }.toPersistentList()
        }
        dispatch(MainAction.OnSearchPerformed(visibleArticles))
    }
}
