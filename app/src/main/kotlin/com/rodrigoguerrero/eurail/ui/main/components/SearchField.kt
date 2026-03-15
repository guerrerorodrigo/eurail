package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rodrigoguerrero.eurail.R

@Composable
fun SearchField(
    searchQuery: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val searchContentDescription = stringResource(R.string.search_articles_description)
    TextField(
        value = searchQuery,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .semantics {
                contentDescription = searchContentDescription
            },
        placeholder = {
            Text(text = stringResource(R.string.search))
        }
    )
}
