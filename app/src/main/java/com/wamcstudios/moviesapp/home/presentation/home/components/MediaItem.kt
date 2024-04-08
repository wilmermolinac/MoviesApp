package com.wamcstudios.moviesapp.home.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.ui.components.RatingItem

@Composable
fun MediaItem(modifier: Modifier = Modifier, item: Media, onMediaClick: () -> Unit) {

    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val imageUrl = "${Constants.IMAGE_BASE_URL}${item.backdropPath}"

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(imageUrl).size(Size.ORIGINAL).build()
    )

    val imageState = imagePainter.state

    Card(modifier = modifier.width(spacing.horizontalFeedItemPosterWidth),
        onClick = { onMediaClick() }) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(spacing.horizontalFeedItemPosterHeight)
                    .fillMaxWidth()
            ) {

                if (imageState is AsyncImagePainter.State.Success) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = imageState.result.drawable.toBitmap(),
                        contentDescription = item.title, contentScale = ContentScale.Crop,
                    )
                }

                if (imageState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                if (imageState is AsyncImagePainter.State.Error) {
                    Icon(
                        modifier = Modifier
                            .size(spacing.iconButtonSizeExtra + spacing.iconButtonSizeExtra)
                            .align(Alignment.Center),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }


                RatingItem(
                    modifier = Modifier
                        .padding(spacing.spaceMicroSmall)
                        .align(Alignment.TopEnd),
                    rating = item.voteAverage
                )


            }

            Column(modifier = Modifier.padding(spacing.spaceMicroSmall)) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (item.mediaType == MediaType.Movie.name) {
                        item.title
                    } else {
                        item.originalName
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    minLines = 2,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )

                Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))

                Text(
                    text = item.genres.joinToString(separator = ", ").ifEmpty {
                        stringResource(id = R.string.no_genre)
                    },
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }


        }

    }
}