package com.jephtecolin.moviecompose.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Movie(
    @NonNull
    var id: String,
    @SerializedName("imdb_id")
    var imdbId: String?,
    var title: String,
    var overview: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("vote_average")
    var voteAverage: Float,
)