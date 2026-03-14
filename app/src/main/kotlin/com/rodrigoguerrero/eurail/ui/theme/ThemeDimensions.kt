package com.rodrigoguerrero.eurail.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ThemeDimensions(
    val padding: Padding = Padding(),
    val elevation: Elevation = Elevation(),
    val radius: Radius = Radius(),
    val border: Border = Border(),
    val size: Size = Size(),
)

data class Padding(
    val none: Dp = 0.dp,
    val xxxxs: Dp = 1.dp,
    val xxxs: Dp = 2.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 20.dp,
    val xl: Dp = 24.dp,
    val xl2: Dp = 32.dp,
    val xl3: Dp = 40.dp,
    val xl4: Dp = 48.dp,
    val xl5: Dp = 64.dp,
    val xl6: Dp = 72.dp,
    val xl7: Dp = 80.dp,
    val xl8: Dp = 88.dp,
    val xl9: Dp = 96.dp,
    val xl10: Dp = 104.dp,
    val xl11: Dp = 112.dp,
)

data class Elevation(
    val xs: Dp = 8.dp,
    val md: Dp = 16.dp,
)

data class Radius (
    val none: Dp = 0.dp,
    val xxxs: Dp = 2.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 20.dp,
    val rounded: Dp = 112.dp,
)

data class Border (
    val none: Dp = 0.dp,
    val sm: Dp = 1.dp,
    val md: Dp = 2.dp,
    val lg: Dp = 4.dp,
)

data class Size (
    val none: Dp = 0.dp,
    val xxxxs: Dp = 1.dp,
    val xxxs: Dp = 2.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 20.dp,
    val xl: Dp = 24.dp,
    val xl2: Dp = 32.dp,
    val xl3: Dp = 40.dp,
    val xl4: Dp = 48.dp,
    val xl5: Dp = 64.dp,
    val xl6: Dp = 72.dp,
    val xl7: Dp = 80.dp,
    val xl8: Dp = 88.dp,
    val xl9: Dp = 96.dp,
)
