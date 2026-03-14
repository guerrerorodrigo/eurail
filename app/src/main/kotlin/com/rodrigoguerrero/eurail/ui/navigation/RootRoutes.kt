package com.rodrigoguerrero.eurail.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class RootRoutes {
    @Serializable
    data object List : RootRoutes()

    @Serializable
    data class Details(val id: Int) : RootRoutes()
}
