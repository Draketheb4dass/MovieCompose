package com.jephtecolin.moviecompose.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jephtecolin.moviecompose.data.model.Movie
import com.jephtecolin.moviecompose.data.model.MovieCategory
import com.jephtecolin.moviecompose.data.model.getMovieCategory
import com.jephtecolin.moviecompose.data.remote.NetworkState
import com.jephtecolin.moviecompose.extensions.paging
import com.jephtecolin.moviecompose.ui.MovieCard
import com.jephtecolin.moviecompose.ui.components.ChipGroup

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

            Column(modifier = Modifier.fillMaxSize()) {
                ChipGroup(selectedMovieCategory = viewModel.selectedMovieCategory.value,
                onSelectedChanged = {viewModel.selectedMovieCategory.value = getMovieCategory(it) }
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = 24.dp)
                ) {

                    paging(
                        items = movies.value,
                        currentIndexFlow = viewModel.moviePageStateFlow,
                        fetch = {
                            viewModel.fetchNextMoviePage()
                        }
                    ) {
                        MovieCard(movie = it)
                    }
                }
        }

        if (networkState == NetworkState.LOADING) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}