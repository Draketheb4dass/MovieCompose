package com.jephtecolin.moviecompose.data.model


enum class TVShowCategory(val value: String) {
    TOP_RATED("Top Rated"),
    POPULAR("Popular"),
    ON_TV("On TV"),
    AIRING_TODAY("Airing Today")

}

fun getAllTVShowCategories(): List<TVShowCategory> = listOf(
    TVShowCategory.TOP_RATED,
    TVShowCategory.POPULAR,
    TVShowCategory.ON_TV,
    TVShowCategory.AIRING_TODAY
)


fun getTVShowCategory(value: String): TVShowCategory {
    val map = TVShowCategory.values().associateBy(TVShowCategory::value)
    return map[value] ?: TVShowCategory.TOP_RATED
}