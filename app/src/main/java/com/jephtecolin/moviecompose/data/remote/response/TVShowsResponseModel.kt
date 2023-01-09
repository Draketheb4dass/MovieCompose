package com.jephtecolin.moviecompose.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jephtecolin.moviecompose.data.model.TVShow

data class TVShowsResponseModel(
    val page: Int,
    val results: List<TVShow>,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("total_pages")
    val totalPage: Int
)