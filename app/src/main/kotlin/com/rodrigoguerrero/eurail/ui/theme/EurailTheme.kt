package com.rodrigoguerrero.eurail.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object EurailTheme {
    val dimens: ThemeDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}
