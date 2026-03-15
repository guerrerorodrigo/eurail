package com.rodrigoguerrero.eurail.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.semantics
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.ui.common.LifecycleEffect
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenLoader
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessage
import com.rodrigoguerrero.eurail.ui.main.components.ArticleCard
import com.rodrigoguerrero.eurail.ui.main.components.ArticleCardState
import com.rodrigoguerrero.eurail.ui.main.components.SearchField
import com.rodrigoguerrero.eurail.ui.navigation.RootRoutes
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun MainScreen(
    navigateTo: (RootRoutes) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val errorMessage = stringResource(R.string.there_is_no_internet_connection)
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEffect(
        onResume = { viewModel.dispatch(MainAction.OnResume) },
        onPause = { viewModel.dispatch(MainAction.OnPause) },
    )

    LaunchedEffect(state.isNetworkAvailable) {
        if (!state.isNetworkAvailable) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Indefinite,
            )
        } else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            SearchField(
                searchQuery = state.searchQuery,
                onValueChanged = { viewModel.dispatch(MainAction.OnSearchQueryChanged(it)) },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        when {
            state.isLoading -> FullScreenLoader()
            state.fullScreenMessageState != null -> state.fullScreenMessageState?.let {
                FullScreenMessage(
                    onClick = { viewModel.dispatch(MainAction.OnRetry) },
                    state = it,
                )
            }

            else -> ArticlesContent(
                articles = state.visibleArticles,
                modifier = Modifier.padding(paddingValues),
                onClick = { navigateTo(RootRoutes.Details(id = it)) },
            )
        }

    }
}

@Composable
private fun ArticlesContent(
    articles: ImmutableList<IndexedValue<ArticleCardState>>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = articles.size,
                    columnCount = 1,
                )
            }
            .padding(horizontal = EurailTheme.dimens.padding.md)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(EurailTheme.dimens.padding.md),
        contentPadding = PaddingValues(vertical = EurailTheme.dimens.padding.sm),
    ) {
        items(items = articles, key = { it.value.id }) { article ->
            ArticleCard(
                state = article.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(article.value.id) },
                index = article.index,
            )
        }
    }
}
