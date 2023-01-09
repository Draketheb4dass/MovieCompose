package com.jephtecolin.moviecompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jephtecolin.moviecompose.data.model.Season
import com.jephtecolin.moviecompose.data.remote.Api
import com.jephtecolin.moviecompose.ui.theme.TextColor

@Composable
fun SeasonCard(season: Season, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(150.dp)
            .padding(horizontal = 24.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = modifier) {
            AsyncImage(modifier = Modifier.weight(2F),
                model = Api.getPosterPath(season.posterPath),
                contentDescription = "")
            Column(modifier = Modifier.weight(5F).padding(10.dp)) {
                Text(modifier = Modifier.padding(bottom = 5.dp), fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    text = "Season ${season.seasonNumber}",
                    color = TextColor)
                Text(modifier = Modifier.padding(bottom = 5.dp), fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "${season.episodeCount} Episodes",
                    color = MaterialTheme.colorScheme.primary)
                Text(season.overview,
                    maxLines = 3,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = TextColor)
            }
        }
    }
    
}