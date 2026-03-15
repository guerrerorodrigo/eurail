package com.rodrigoguerrero.eurail.ui.main.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText

internal class ArticleCardRobot(
    private val rule: ComposeTestRule,
) {
    fun assertTitle(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertSummary(value: String) {
        rule.assertIsDisplayed(value)
    }

    fun assertUpdateDate(value: String) {
        rule.assertIsDisplayed(value)
    }

    private fun ComposeTestRule.assertIsDisplayed(value: String) {
        onNodeWithText(text = value)
            .assertIsDisplayed()
    }
}

internal fun articleCard(
    rule: ComposeTestRule,
    block: ArticleCardRobot.() -> Unit
) {
    ArticleCardRobot(rule).apply(block)
}
