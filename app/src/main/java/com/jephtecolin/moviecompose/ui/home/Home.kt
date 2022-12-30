package com.jephtecolin.moviecompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jephtecolin.moviecompose.data.model.Movie
import com.jephtecolin.moviecompose.data.remote.NetworkState
import com.jephtecolin.moviecompose.extensions.paging
import com.jephtecolin.moviecompose.ui.MovieCard

@Composable
fun Home(
    viewModel: HomeViewModel,
    navigateToMovie: (String) -> Unit
) {
    val networkState: NetworkState by viewModel.movieLoadingState
    val movies: State<List<Movie>> =  viewModel.movies
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        if (networkState == NetworkState.SUCCESS) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .statusBarsPadding().padding(horizontal = 24.dp)
            ) {

                paging(
                    items = movies.value,
                    currentIndexFlow = viewModel.moviePageStateFlow,
                    fetch = {
                        //viewModel.fetchNextMoviePage()
                    }
                ) {
                    MovieCard(movie = it)
//                MoviePoster(
//                    movie = it,
//                    selectPoster = selectPoster
//                )
                }
            }
        }
    }
}