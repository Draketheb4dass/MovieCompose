package com.jephtecolin.moviecompose.data.remote

import com.jephtecolin.moviecompose.data.model.TVShowDetail
import com.jephtecolin.moviecompose.data.remote.response.TVShowsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(@Query("page") page: Int) : Response<TVShowsResponseModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(@Query("page") page: Int) : Response<TVShowsResponseModel>

    @GET("tv/popular")
    suspend fun getPopularTVShows(@Query("page") page: Int) : Response<TVShowsResponseModel>

    @GET("tv/on_the_air")
    suspend fun getOnTVTVShows(@Query("page") page: Int) : Response<TVShowsResponseModel>

    @GET("/tv/{tv_id}")
    suspend fun getTVShowDetail(@Path("tv_id") id: Long) : Response<TVShowDetail>
}