package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.remote.response.MoviesResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val tmdbService: TMDBService) {
    fun getNowPlayingMovies(): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getNowPlayingMovies()
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching now playing movies: %s", result.errorBody())
        }
    }

    fun getTopRatedMovies(): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getTopRatedMovies()
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching top rated movies: %s", result.errorBody())
        }
    }

    fun getPopularMovies(): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getPopularMovies()
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching popular movies: %s", result.errorBody())
        }
    }

}