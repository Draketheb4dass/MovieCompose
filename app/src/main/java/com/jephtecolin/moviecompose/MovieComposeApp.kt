package com.jephtecolin.moviecompose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jephtecolin.moviecompose.ui.home.Home
import com.jephtecolin.moviecompose.ui.tvdetail.TVShowDetail


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
                viewModel = hiltViewModel(),
                navigateToMovie = { tvId ->
                    appState.navigateToTVShow("${Screen.TVShowDetail.route}/${tvId}", backStackEntry)
                }
            )
        }
        composable("${Screen.TVShowDetail.route}/{tvId}" , arguments = listOf(navArgument("tvId"){
            type = NavType.LongType
        })) { backStackEntry ->
            TVShowDetail(
                tvId = backStackEntry.arguments!!.getLong("tvId"),
                viewModel = hiltViewModel(),
                onBackPressed = { appState.navigateBack()})
        }

    }
}