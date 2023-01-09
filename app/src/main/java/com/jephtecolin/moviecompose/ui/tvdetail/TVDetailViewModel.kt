package com.jephtecolin.moviecompose.ui.tvdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.moviecompose.data.model.TVShow
import com.jephtecolin.moviecompose.data.model.TVShowDetail
import com.jephtecolin.moviecompose.data.remote.NetworkState
import com.jephtecolin.moviecompose.data.remote.TVShowDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TVDetailViewModel @Inject constructor(
    tvShowDataSource: TVShowDataSource
) : ViewModel() {

    private val _tvLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val tvLoadingState: State<NetworkState> get() = _tvLoadingState

    var tvDetail: MutableState<TVShowDetail?> = mutableStateOf(null)

    val tvId: MutableStateFlow<Long?> = MutableStateFlow(null)

    private val detailFlow = tvId.flatMapLatest { tvId ->

        if (tvId != null) {
            tvShowDataSource.getTVShowDetail(tvId)
                .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
                .catch { e ->
                    Timber.e(e)
                    _tvLoadingState.value = NetworkState.ERROR
                }
        } else {
            emptyFlow()
        }


    }

    init {
        viewModelScope.launch {
            detailFlow.collectLatest {
                tvDetail.value = it!!
            }
        }

    }
}