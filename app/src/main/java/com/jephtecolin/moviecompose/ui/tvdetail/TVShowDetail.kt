package com.jephtecolin.moviecompose.ui.tvdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.jephtecolin.moviecompose.R
import com.jephtecolin.moviecompose.data.model.Season
import com.jephtecolin.moviecompose.data.remote.Api
import com.jephtecolin.moviecompose.ui.components.SeasonCard
import com.jephtecolin.moviecompose.ui.theme.TextColor


@Composable
fun TVShowDetail(
    tvId: Long,
    viewModel: TVDetailViewModel,
    onBackPressed: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.tvId.value = tvId
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(enabled = true, state = ScrollState(0))) {
        viewModel.tvDetail.value?.voteAverage?.let {
            TVShowPoster(imageUrl = Api.getPosterPath(viewModel.tvDetail.value?.backdropPath),
                imageHeight = 300.dp, rating = it, originalName = viewModel.tvDetail.value?.originalName?:"",
                name = viewModel.tvDetail.value?.name?:""
            )
        }
        TVShowSummary(summary = viewModel.tvDetail.value?.overview?: "")
        viewModel.tvDetail.value?.seasons?.let { TVShowSeasonList(seasons = it) }
    }
}

// TODO This func has too many param
@Composable
private fun TVShowPoster(
    imageUrl: String,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colorScheme.onError,
    rating: Float,
    name: String,
    originalName: String,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl)
            .crossfade(true)
            .build()
    )

    Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.BottomStart) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(imageHeight)
        )
        Column(modifier = Modifier.padding(start = 24.dp)) {
            Text(originalName, fontSize = 12.sp, color = Color.White)
            Text(name, fontSize = 34.sp, color = Color.White)
            RatingBar(
                modifier = Modifier.height(50.dp),
                rating = rating.toDouble()/2,
                stars = 5,
            )
        }

    }



    if (painter.state is AsyncImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(placeholderColor)
        )
    }
}

@Composable
private fun TVShowSummary(summary: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        if (summary.isNotEmpty()) {
            Text(modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 20.sp, text = "Summary",
                color = MaterialTheme.colorScheme.primary)
            Text(fontSize = 14.sp, text = summary, color = TextColor, lineHeight = 14.sp)
        }

    }
}

@Composable
private fun TVShowSeasonList(seasons: List<Season>) {
    seasons.map {
        SeasonCard(season = it)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Image( painter = painterResource(id = R.drawable.ic_star), contentDescription = "star")
            Spacer(modifier = Modifier.width(4.dp))
        }

        if (halfStar) {
            Image(painter = painterResource(id = R.drawable.ic_half_star), contentDescription = "half star")
            Spacer(modifier = Modifier.width(4.dp))
        }

        repeat(unfilledStars) {
            Image(painter = painterResource(id = R.drawable.ic_unfilled_star), contentDescription = "unfilled star")
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}