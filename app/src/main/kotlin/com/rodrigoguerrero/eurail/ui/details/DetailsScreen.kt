package com.rodrigoguerrero.eurail.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenLoader
import com.rodrigoguerrero.eurail.ui.common.components.FullScreenMessage
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = state.title)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back),
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading -> FullScreenLoader()
            state.fullScreenMessageState != null -> state.fullScreenMessageState?.let {
                FullScreenMessage(
                    onClick = { viewModel.dispatch(DetailsAction.OnRetry(state.id)) },
                    state = it,
                )
            }

            else -> Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState()),
            ) {
                MarkdownText(
                    markdown = state.content,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(all = EurailTheme.dimens.padding.md),
                )
            }
        }
    }
}
