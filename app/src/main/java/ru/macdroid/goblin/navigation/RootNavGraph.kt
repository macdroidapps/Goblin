package ru.macdroid.goblin.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.macdroid.goblin.presentation.LoginRoot

@Composable
fun RootNavGraph(
    navController: NavHostController,
) {

    val startDestination = Screen.Login.route

    NavHost(
        navController = navController,
        route = Route.ROOT,
        startDestination = startDestination
    ) {

        composable(route = Screen.Login.route) {
            LoginRoot(
                onNavigateNext = {
                    Log.d("happy", "RootNavGraph")
                }
            )
        }


    }
}
