package ru.itis.android.uprising26.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.itis.android.uprising26.presentation.ui.DetailScreen
import ru.itis.android.uprising26.presentation.ui.MainScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
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
    }
}