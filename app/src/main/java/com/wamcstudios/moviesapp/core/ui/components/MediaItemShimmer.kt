package com.wamcstudios.moviesapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing

@Composable
fun MediaItemShimmer(modifier: Modifier = Modifier, onMediaClick: () -> Unit) {

    val spacing = LocalSpacing.current


    Card(modifier = modifier.width(spacing.horizontalFeedItemPosterWidth),
        onClick = { onMediaClick() }) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(spacing.horizontalFeedItemPosterHeight)
                    .fillMaxWidth()
            ) {

                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                RatingItem(
                    modifier = Modifier
                        .padding(spacing.spaceMicroSmall)
                        .align(Alignment.TopEnd),
                    rating = 0.0, isLoadShimmer = true
                )


            }

            Column(modifier = Modifier.padding(spacing.spaceMicroSmall)) {

                Box(
                    modifier = Modifier
                        .height(spacing.spaceSmall)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
                        .clip(
                            CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                Box(
                    modifier = Modifier
                        .height(spacing.spaceSmall)
                        .width(spacing.spaceLargeExtraMax)
                        .background(color = Color.White, shape = CircleShape)
                        .clip(
                            CircleShape
                        )
                )
            }


        }

    }
}

@Preview
@Composable
fun MediaItemShimmerPreview() {
    MediaItemShimmer(onMediaClick = {})
}