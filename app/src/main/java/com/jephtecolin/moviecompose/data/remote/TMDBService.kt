package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.remote.response.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int) : Response<MoviesResponseModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int) : Response<MoviesResponseModel>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int) : Response<MoviesResponseModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int) : Response<MoviesResponseModel>
}