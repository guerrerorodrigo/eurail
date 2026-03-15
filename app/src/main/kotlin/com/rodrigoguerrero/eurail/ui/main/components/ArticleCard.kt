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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.font.FontWeight
import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.ui.theme.EurailTheme
import com.rodrigoguerrero.eurail.ui.utils.PreviewBox
import com.rodrigoguerrero.eurail.ui.utils.WidgetPreviews

@Composable
internal fun ArticleCard(
    state: ArticleCardState,
    index: Int,
    modifier: Modifier = Modifier,
) {
    val cardContentDescription = stringResource(
        R.string.card_content_description,
        state.title,
        state.updatedDate,
        state.summary,
    )
    Card(
        modifier = modifier.clearAndSetSemantics(
            properties = {
                role = Role.Button
                contentDescription = cardContentDescription
                collectionItemInfo = CollectionItemInfo(
                    rowIndex = index,
                    rowSpan = 1,
                    columnIndex = 0,
                    columnSpan = 1,
                )
            },
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
        ArticleCard(state = state, index = 0)
    }
}