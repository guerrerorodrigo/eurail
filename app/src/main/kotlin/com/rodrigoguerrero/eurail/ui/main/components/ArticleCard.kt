package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme
import com.rodrigoguerrero.eurail.ui.utils.PreviewBox
import com.rodrigoguerrero.eurail.ui.utils.WidgetPreviews

@Composable
internal fun ArticleCard(
    state: ArticleCardState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.semantics( // TODO: pass CollectionInfo
            mergeDescendants = true,
            properties = { },
        ),
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = EurailTheme.dimens.elevation.xs,
        ),
        shape = RoundedCornerShape(size = EurailTheme.dimens.radius.xs),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(EurailTheme.dimens.padding.md),
            modifier = Modifier.padding(all = EurailTheme.dimens.padding.md),
        ) {
            Column {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
                Text(text = state.updatedDate, style = MaterialTheme.typography.labelMedium)
            }
            Text(text = state.summary, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewArticleCard() {
    val state = ArticleCardState(
        title = "Article Title",
        updatedDate = "31 May 2025",
        summary = "This is a short summary about the article.",
        id = 1,
    )
    PreviewBox {
        ArticleCard(state = state)
    }
}