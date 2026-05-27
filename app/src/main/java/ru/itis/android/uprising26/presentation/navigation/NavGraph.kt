package ru.itis.android.uprising26.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.itis.android.uprising26.presentation.ui.DetailScreen
import ru.itis.android.uprising26.presentation.ui.MainScreen
import ru.itis.android.uprising26.firebase.AnalyticsLogger
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.android.uprising26.presentation.customview.CustomChartScreen

@Composable
fun AppNavGraph(
    analyticsLogger: AnalyticsLogger = hiltViewModel<AnalyticsViewModel>().logger
) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            val route = backStackEntry.destination.route
            if (route != null) {
                analyticsLogger.logScreenView(route)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "custom_chart"
    ) {
        composable("main") {
            MainScreen(
                onSongClick = { songId ->
                    navController.navigate("detail/$songId")
                }
            )
        }

        composable(
            route = "detail/{songId}",
            arguments = listOf(
                navArgument("songId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val songId = backStackEntry.arguments?.getLong("songId") ?: 0L
            DetailScreen(
                songId = songId,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable("custom_chart") {
            CustomChartScreen()
        }
    }
}

@dagger.hilt.android.lifecycle.HiltViewModel
class AnalyticsViewModel @javax.inject.Inject constructor(
    val logger: AnalyticsLogger
) : androidx.lifecycle.ViewModel()
