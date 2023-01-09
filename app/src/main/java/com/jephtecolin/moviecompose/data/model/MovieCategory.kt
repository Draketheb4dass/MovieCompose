package com.jephtecolin.moviecompose.data.model


enum class MovieCategory(val value: String) {
    TOP_RATED("Top Rated"),
    POPULAR("Popular"),
    ON_TV("On TV"),
    AIRING_TODAY("Airing Today")

}

fun getAllMovieCategories(): List<MovieCategory> = listOf(
    MovieCategory.TOP_RATED,
    MovieCategory.POPULAR,
    MovieCategory.ON_TV,
    MovieCategory.AIRING_TODAY
)


fun getMovieCategory(value: String): MovieCategory {
    val map = MovieCategory.values().associateBy(MovieCategory::value)
    return map[value] ?: MovieCategory.TOP_RATED
}