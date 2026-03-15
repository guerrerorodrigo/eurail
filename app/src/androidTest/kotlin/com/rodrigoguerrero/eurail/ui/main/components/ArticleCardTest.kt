package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

internal class ArticleCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test() {
        composeTestRule.setContent {
            ArticleCard(
                state = ArticleCardState(
                    id = 1,
                    title = "card title",
                    summary = "card summary",
                    updatedDate = "updated date",
                )
            )
        }

        articleCard(composeTestRule) {
            assertTitle("card title")
            assertSummary("card summary")
            assertUpdateDate("updated date")
        }
    }
}
