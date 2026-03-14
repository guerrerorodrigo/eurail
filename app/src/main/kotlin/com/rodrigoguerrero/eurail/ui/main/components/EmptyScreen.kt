package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme
import com.rodrigoguerrero.eurail.ui.utils.PreviewBox
import com.rodrigoguerrero.eurail.ui.utils.WidgetPreviews

@Composable
internal fun EmptyScreen(
    onReload: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(EurailTheme.dimens.padding.md),
            modifier = Modifier.padding(horizontal = EurailTheme.dimens.padding.md),
        ) {
            Text(
                text = stringResource(R.string.empty_screen_message),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onReload,
            ) {
                Text(text = stringResource(R.string.reload))
            }
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewEmptyScreen() {
    PreviewBox {
        EmptyScreen(onReload = {})
    }
}
