package com.jephtecolin.moviecompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.jephtecolin.moviecompose.R
import com.jephtecolin.moviecompose.data.model.TVShow
import com.jephtecolin.moviecompose.data.remote.Api
import java.math.RoundingMode

@Composable
fun TVShowCard(tvShow: TVShow, modifier: Modifier = Modifier, onClickCard: (String) -> Unit) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = {
                    onClickCard(tvShow.id)
                }
            ),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(0.6f)
                    .padding(top = 0.dp)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                model = Api.getPosterPath(tvShow.posterPath),
                contentDescription = "",
                placeholder = ColorPainter(Color.Gray),
                error = ColorPainter(Color.DarkGray)
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = tvShow.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            RatingBar(
                modifier = Modifier
                    .constrainAs(content) {
                        start.linkTo(parent.start)
                        top.linkTo(title.bottom)
                    }
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp),
                rating = tvShow.voteAverage.toDouble()/2,
                stars = 5,
            )
        }
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
            Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "star")
        }

        if (halfStar) {
            Image(painter = painterResource(id = R.drawable.ic_half_star), contentDescription = "half star")
        }

        repeat(unfilledStars) {
            Image(painter = painterResource(id = R.drawable.ic_unfilled_star), contentDescription = "unfilled star")
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = (rating.toBigDecimal().setScale(1, RoundingMode.UP).toDouble().toString()), fontSize = 12.sp)
    }
}

