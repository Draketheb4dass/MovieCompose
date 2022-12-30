package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.remote.response.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<MoviesResponseModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies() : Response<MoviesResponseModel>

    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<MoviesResponseModel>
}