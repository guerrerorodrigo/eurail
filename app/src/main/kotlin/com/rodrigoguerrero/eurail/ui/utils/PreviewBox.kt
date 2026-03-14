package com.rodrigoguerrero.eurail.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.rodrigoguerrero.eurail.ui.theme.AppTheme
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme

@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    padding: Dp = EurailTheme.dimens.padding.md,
    content: @Composable () -> Unit,
) {
    AppTheme(
        darkTheme = isSystemInDarkTheme(),
    ) {
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(all = padding),
        ) {
            content()
        }
    }
}
