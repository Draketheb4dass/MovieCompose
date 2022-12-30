package com.jephtecolin.moviecompose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jephtecolin.moviecompose.ui.home.Home
import dagger.hilt.android.lifecycle.HiltViewModel


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
                navigateToMovie = { movieId ->
                    appState.navigateToMovie(movieId, backStackEntry)
                }
            )
        }
//        composable(Screen.Movie.route) { backStackEntry ->
//            val playerViewModel: PlayerViewModel = viewModel(
//                factory = PlayerViewModel.provideFactory(
//                    owner = backStackEntry,
//                    defaultArgs = backStackEntry.arguments
//                )
//            )
//            PlayerScreen(playerViewModel, devicePosture, onBackPress = appState::navigateBack)
//        }

    }
}