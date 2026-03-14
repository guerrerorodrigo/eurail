package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractor
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
            MainAction.Load -> loadArticles()
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
                    onSuccess = { articles ->
                        dispatch(MainAction.OnArticlesLoaded(articles = articles.toArticleCardState()))
                    },
                    onFailure = {}, // TODO handle errors
                )
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
