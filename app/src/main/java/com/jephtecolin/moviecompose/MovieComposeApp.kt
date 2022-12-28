package com.jephtecolin.moviecompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jephtecolin.moviecompose.ui.home.Home

@Composable
fun MovieComposeApp(
    appState: MovieComposeAppState = rememberMovieComposeAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { backStackEntry ->
            Home(
                navigateToMovie = { movieId ->
                    appState.navigateToMovie(movieId, backStackEntry)
                }
            )
        }


    }
}