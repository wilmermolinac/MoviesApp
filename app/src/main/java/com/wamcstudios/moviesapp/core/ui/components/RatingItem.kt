package com.wamcstudios.moviesapp.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.aifusion.core.desingsystem.theme.Orange
import com.wamcstudios.aifusion.core.desingsystem.theme.Soft
import com.wamcstudios.moviesapp.R

@Composable
fun RatingItem(modifier: Modifier = Modifier, rating: Double, isLoadShimmer:Boolean = false) {
    val spacing = LocalSpacing.current

    val containerColor = if (isLoadShimmer){
        Orange
    }else{
        Soft.copy(alpha = 0.72f)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(spacing.spaceSmall),
        colors = CardDefaults.cardColors(
            contentColor = Orange,
            containerColor = containerColor
        )
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = spacing.spaceMicroSmall,
                vertical = spacing.spaceNanoSmall
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (rating == 0.0) {
                Icon(
                    modifier = Modifier.size(spacing.iconSize),
                    imageVector = Icons.Default.StarOutline,
                    contentDescription = stringResource(id = R.string.rating)
                )
            }

            if (rating > 0.0 && rating <= 5.0) {
                Icon(
                    modifier = Modifier.size(spacing.iconSize),
                    imageVector = Icons.AutoMirrored.Default.StarHalf,
                    contentDescription = stringResource(
                        id = R.string.rating
                    )
                )
            }

            if (rating > 5.0) {
                Icon(
                    modifier = Modifier.size(spacing.iconSize),
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.rating)
                )
            }

            Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))

            Text(
                text = if (rating == 0.0) {
                    stringResource(id = R.string.not_rated)
                } else {
                    rating.toString().substring(0, endIndex = 3)
                }, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )

        }

    }
}

@Preview
@Composable
fun RatingItemPreview() {
    RatingItem(rating = 9.9)
}