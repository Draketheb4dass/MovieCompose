package com.jephtecolin.moviecompose.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.moviecompose.data.model.Movie
import com.jephtecolin.moviecompose.data.model.MovieCategory
import com.jephtecolin.moviecompose.data.remote.MovieDataSource
import com.jephtecolin.moviecompose.data.remote.NetworkState
import com.jephtecolin.moviecompose.data.remote.response.MoviesResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieDataSource: MovieDataSource
) : ViewModel(){

    private val _movieLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val movieLoadingState: State<NetworkState> get() = _movieLoadingState
    val selectedMovieCategory: MutableStateFlow<MovieCategory> = MutableStateFlow(MovieCategory.TOP_RATED)

    val movies: State<MutableList<Movie>> = mutableStateOf(mutableListOf())

    val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)

    private val newMovieFlow = moviePageStateFlow.flatMapLatest {
        _movieLoadingState.value = NetworkState.LOADING

        when (selectedMovieCategory.value) {
            MovieCategory.TOP_RATED -> {
                movieDataSource.getTopRatedMovies(moviePageStateFlow.value)
                    .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _movieLoadingState.value = NetworkState.ERROR
                    }
            }
            MovieCategory.POPULAR -> {
                movieDataSource.getPopularMovies(moviePageStateFlow.value)
                    .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _movieLoadingState.value = NetworkState.ERROR
                    }
            }
            MovieCategory.ON_TV -> {
                movieDataSource.getUpcomingMovies(moviePageStateFlow.value)
                    .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _movieLoadingState.value = NetworkState.ERROR
                    }
            }
            MovieCategory.AIRING_TODAY -> {
                movieDataSource.getNowPlayingMovies(moviePageStateFlow.value)
                    .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _movieLoadingState.value = NetworkState.ERROR
                    }
            }
        }


    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    private val newMovieFlo = selectedMovieCategory.flatMapLatest {
            _movieLoadingState.value = NetworkState.LOADING

            when (selectedMovieCategory.value) {
                MovieCategory.TOP_RATED -> {
                    movieDataSource.getTopRatedMovies()
                        .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _movieLoadingState.value = NetworkState.ERROR
                        }
                }
                MovieCategory.POPULAR -> {
                    movieDataSource.getPopularMovies()
                        .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _movieLoadingState.value = NetworkState.ERROR
                        }
                }
                MovieCategory.ON_TV -> {
                    movieDataSource.getUpcomingMovies()
                        .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _movieLoadingState.value = NetworkState.ERROR
                        }
                }
                MovieCategory.AIRING_TODAY -> {
                    movieDataSource.getNowPlayingMovies()
                        .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _movieLoadingState.value = NetworkState.ERROR
                        }
                }
            }


        }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)



    fun fetchNextMoviePage() {
        if (movieLoadingState.value != NetworkState.LOADING) {
            moviePageStateFlow.value++
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newMovieFlow.collectLatest {
                movies.value.addAll(it!!.results)
                Timber.d(movies.value.size.toString())
            }
        }

        viewModelScope.launch {
            newMovieFlo.collectLatest {
                movies.value.clear()
                movies.value.addAll(it!!.results)
            }
        }
    }
}