package com.wamcstudios.moviesapp.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing

@Composable
fun UpcomingMovieItemShimmer(modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(spacing.upcomingMovieItemHeight),
        shape = RoundedCornerShape(spacing.spaceMedium),
        onClick = {}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Column(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {


                Surface(
                    modifier = Modifier
                        .padding(end = spacing.spaceMediumExtra)
                        .fillMaxWidth()
                        .height(spacing.spaceMedium),
                    shape = CircleShape,
                    color = Color.DarkGray
                ) {
                    Text(
                        modifier = Modifier
                            .padding(spacing.spaceNanoSmall),
                        text = "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Start
                    )
                }


                Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                Surface(
                    modifier = Modifier
                        .padding(end = spacing.spaceMediumExtra)
                        .width(spacing.spaceLargeExtraMax)
                        .height(spacing.spaceMediumSmall),
                    shape = CircleShape,
                    color = Color.White
                ) {
                    Text(
                        modifier = Modifier.padding(spacing.spaceNanoSmall),
                        text = "", color = Color.White
                    )
                }
            }


        }
    }
}

@Preview
@Composable
fun UpcomingMovieItemShimmerPreview() {
    UpcomingMovieItemShimmer()

}