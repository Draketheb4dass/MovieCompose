package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.remote.response.MoviesResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val tmdbService: TMDBService) {
    fun getNowPlayingMovies(page: Int = 1): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getNowPlayingMovies(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching now playing movies: %s", result.errorBody())
        }
    }

    fun getTopRatedMovies(page: Int = 1): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getTopRatedMovies(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching top rated movies: %s", result.errorBody())
        }
    }

    fun getPopularMovies(page: Int = 1): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getPopularMovies(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching popular movies: %s", result.errorBody())
        }
    }

    fun getUpcomingMovies(page: Int = 1): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getUpcomingMovies(page)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching latest movies: %s", result.errorBody())
        }
    }

}