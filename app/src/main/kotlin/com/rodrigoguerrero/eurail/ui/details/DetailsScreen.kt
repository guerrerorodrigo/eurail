package com.rodrigoguerrero.eurail.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DetailsScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold { paddingValues ->
        Text(text = "details", modifier.padding(paddingValues))
    }
}
