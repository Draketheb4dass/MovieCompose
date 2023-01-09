package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.model.TVShowDetail
import com.jephtecolin.moviecompose.data.remote.response.TVShowsResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TVShowDataSource @Inject constructor(private val tmdbService: TMDBService) {
    fun getAiringTodayTVShows(page: Int = 1): Flow<TVShowsResponseModel?> = flow {
        val result = tmdbService.getAiringTodayTvShows(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching now playing movies: %s", result.errorBody())
        }
    }

    fun getTopRatedTVShows(page: Int = 1): Flow<TVShowsResponseModel?> = flow {
        val result = tmdbService.getTopRatedTVShows(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching top rated movies: %s", result.errorBody())
        }
    }

    fun getPopularTVShows(page: Int = 1): Flow<TVShowsResponseModel?> = flow {
        val result = tmdbService.getPopularTVShows(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching popular movies: %s", result.errorBody())
        }
    }

    fun getTVTVShows(page: Int = 1): Flow<TVShowsResponseModel?> = flow {
        val result = tmdbService.getOnTVTVShows(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching latest movies: %s", result.errorBody())
        }
    }

    fun getTVShowDetail(id: Long): Flow<TVShowDetail?> = flow {
        val result = tmdbService.getTVShowDetail(id)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching latest movies: %s", result.errorBody())
        }
    }

}