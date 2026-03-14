package com.rodrigoguerrero.eurail.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.eurail.ui.details.DetailsScreen
import com.rodrigoguerrero.eurail.ui.main.MainScreen

@Composable
internal fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        NavHost(
            navController = navController,
            startDestination = RootRoutes.List,
        ) {
            composable<RootRoutes.List> {
                MainScreen(
                    navigateTo = { route ->
                        navController.navigate(route)
                    },
                )
            }
            composable<RootRoutes.Details> { DetailsScreen() }
        }
    }
}
