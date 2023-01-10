package com.jephtecolin.moviecompose.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class TVShowDetail(
    @NonNull
    var id: String,
    @SerializedName("imdb_id")
    var imdbId: String?,
    var name: String,
    @SerializedName("original_name")
    var originalName: String,
    var overview: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("vote_average")
    var voteAverage: Float,
    @SerializedName("seasons")
    var seasons: List<Season>
)

data class Season(
    @SerializedName("season_number")
    var seasonNumber: Int,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("episode_count")
    var episodeCount: Int
)
