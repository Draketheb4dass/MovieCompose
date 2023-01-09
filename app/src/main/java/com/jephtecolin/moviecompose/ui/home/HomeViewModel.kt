package com.jephtecolin.moviecompose.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.moviecompose.data.model.TVShow
import com.jephtecolin.moviecompose.data.model.TVShowCategory
import com.jephtecolin.moviecompose.data.remote.TVShowDataSource
import com.jephtecolin.moviecompose.data.remote.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    tvShowDataSource: TVShowDataSource
) : ViewModel(){

    private val _tvLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val tvLoadingState: State<NetworkState> get() = _tvLoadingState
    val selectedTVShowCategory: MutableStateFlow<TVShowCategory> = MutableStateFlow(TVShowCategory.TOP_RATED)

    val tvShows: State<MutableList<TVShow>> = mutableStateOf(mutableListOf())

    val tvShowPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)

    private val newTVShowFlow = tvShowPageStateFlow.flatMapLatest {
        _tvLoadingState.value = NetworkState.LOADING

        when (selectedTVShowCategory.value) {
            TVShowCategory.TOP_RATED -> {
                tvShowDataSource.getTopRatedTVShows(tvShowPageStateFlow.value)
                    .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _tvLoadingState.value = NetworkState.ERROR
                    }
            }
            TVShowCategory.POPULAR -> {
                tvShowDataSource.getPopularTVShows(tvShowPageStateFlow.value)
                    .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _tvLoadingState.value = NetworkState.ERROR
                    }
            }
            TVShowCategory.ON_TV -> {
                tvShowDataSource.getTVTVShows(tvShowPageStateFlow.value)
                    .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _tvLoadingState.value = NetworkState.ERROR
                    }
            }
            TVShowCategory.AIRING_TODAY -> {
                tvShowDataSource.getAiringTodayTVShows(tvShowPageStateFlow.value)
                    .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                    .catch { e ->
                        Timber.e(e)
                        _tvLoadingState.value = NetworkState.ERROR
                    }
            }
        }


    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    private val newMovieFlo = selectedTVShowCategory.flatMapLatest {
            _tvLoadingState.value = NetworkState.LOADING

            when (selectedTVShowCategory.value) {
                TVShowCategory.TOP_RATED -> {
                    tvShowDataSource.getTopRatedTVShows()
                        .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _tvLoadingState.value = NetworkState.ERROR
                        }
                }
                TVShowCategory.POPULAR -> {
                    tvShowDataSource.getPopularTVShows()
                        .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _tvLoadingState.value = NetworkState.ERROR
                        }
                }
                TVShowCategory.ON_TV -> {
                    tvShowDataSource.getTVTVShows()
                        .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _tvLoadingState.value = NetworkState.ERROR
                        }
                }
                TVShowCategory.AIRING_TODAY -> {
                    tvShowDataSource.getAiringTodayTVShows()
                        .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                        .catch { e ->
                            Timber.e(e)
                            _tvLoadingState.value = NetworkState.ERROR
                        }
                }
            }


        }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)



    fun fetchNextMoviePage() {
        if (tvLoadingState.value != NetworkState.LOADING) {
            tvShowPageStateFlow.value++
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newTVShowFlow.collectLatest {
                tvShows.value.addAll(it!!.results)
                Timber.d(tvShows.value.size.toString())
            }
        }

        viewModelScope.launch {
            newMovieFlo.collectLatest {
                tvShows.value.clear()
                tvShows.value.addAll(it!!.results)
            }
        }
    }
}