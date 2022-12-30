package com.jephtecolin.moviecompose.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.moviecompose.data.model.Movie
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

    val movies: State<MutableList<Movie>> = mutableStateOf(mutableListOf())
    val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    private val newMovieFlow = moviePageStateFlow.flatMapLatest {
        _movieLoadingState.value = NetworkState.LOADING

        movieDataSource.getNowPlayingMovies()
            .onCompletion { _movieLoadingState.value = NetworkState.SUCCESS }
            .catch { e ->
                Timber.e(e)
                _movieLoadingState.value = NetworkState.ERROR
            }

    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newMovieFlow.collectLatest {
                movies.value.addAll(it!!.results)
                Timber.d(movies.value.size.toString())
            }
        }
    }
}