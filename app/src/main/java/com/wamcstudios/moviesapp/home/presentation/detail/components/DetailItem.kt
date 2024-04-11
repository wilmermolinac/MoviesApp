package com.wamcstudios.moviesapp.home.presentation.detail.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.aifusion.core.desingsystem.theme.Soft
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.ui.components.RatingItem
import com.wamcstudios.moviesapp.home.domain.model.ProductionCompany

private const val TAG = "DetailItem"

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    mediaItem: Media? = null,
    onClickFavorite: () -> Unit,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val imageUrlBackdrop =
        "${com.wamcstudios.moviesapp.core.common.Constants.IMAGE_BASE_URL}${mediaItem?.backdropPath}"
    Log.d(TAG, "")
    val imageUrlPoster =
        "${com.wamcstudios.moviesapp.core.common.Constants.IMAGE_BASE_URL}${mediaItem?.posterPath}"

    val imagePainterBackdrop = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(imageUrlBackdrop).size(Size.ORIGINAL).build()
    )

    val imagePainterPoster = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(imageUrlPoster).size(Size.ORIGINAL).build()
    )


    val imageStateBackdrop = imagePainterBackdrop.state
    val imageStatePoster = imagePainterPoster.state

    Box(modifier = modifier.fillMaxSize()) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.backdropHeight)
        ) {

            if (mediaItem == null) {
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surfaceContainer)
                        .fillMaxSize()
                )
            } else {
                if (imageStateBackdrop is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                if (imageStateBackdrop is AsyncImagePainter.State.Error) {
                    Image(
                        modifier = Modifier.size(spacing.spaceLargeExtraMax + spacing.spaceLargeExtra),
                        imageVector = Icons.Default.ImageNotSupported,
                        contentDescription = mediaItem.title
                    )
                }

                if (imageStateBackdrop is AsyncImagePainter.State.Success) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = imageStateBackdrop.result.drawable.toBitmap(),
                        contentDescription = mediaItem.title, contentScale = ContentScale.Crop,
                    )
                }
            }

            DetailOverlay(
                modifier = Modifier.fillMaxSize(),
                alpha = 0.2f,
                color = MaterialTheme.colorScheme.background
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = spacing.spaceLargeMedium,
                bottom = spacing.spaceMediumExtra
            )
        ) {


            item {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Card(
                        modifier = Modifier
                            .height(spacing.posterHeight)
                            .width(spacing.posterWidth),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (mediaItem != null) {
                                if (imageStatePoster is AsyncImagePainter.State.Error) {

                                    Image(
                                        modifier = Modifier.size(spacing.spaceLargeExtraMax + spacing.spaceLargeExtra),
                                        imageVector = Icons.Default.ImageNotSupported,
                                        contentDescription = mediaItem.title
                                    )

                                }

                                if (imageStatePoster is AsyncImagePainter.State.Loading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                if (imageStatePoster is AsyncImagePainter.State.Success) {
                                    AsyncImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = imageStatePoster.result.drawable.toBitmap(),
                                        contentDescription = mediaItem.title,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }


                    }

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    IconAndTextContainer(
                        textContent = mediaItem?.releaseDate?.year.toString(),
                        description = stringResource(id = R.string.calendar),
                        icon = Icons.Default.CalendarMonth,
                        isItemNull = mediaItem == null
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    val timeRuntimeDetail = if (mediaItem?.mediaType == MediaType.Movie.name) {
                        mediaItem.runtimeDetail.toString()
                    } else {
                        if (mediaItem?.episodeRunTimeTvDetail.isNullOrEmpty()) {
                            stringResource(id = R.string.no_runtime)
                        } else {
                            mediaItem?.episodeRunTimeTvDetail?.last().toString()

                        }

                    }
                    IconAndTextContainer(
                        textContent = stringResource(
                            id = R.string.detail_runtime_text,
                            timeRuntimeDetail.toString()
                        ),
                        description = stringResource(id = R.string.time),
                        icon = Icons.Default.AccessTime,
                        isItemNull = mediaItem == null
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    IconAndTextContainer(
                        textContent = mediaItem?.genresListDetail?.map {
                            it.name
                        }?.joinToString(separator = ", ")
                            ?: stringResource(id = R.string.no_genre),
                        description = stringResource(id = R.string.genres),
                        icon = Icons.Default.PlayCircleFilled,
                        isItemNull = mediaItem == null
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    RatingItem(
                        rating = mediaItem?.voteAverage ?: 0.0,
                        isLoadShimmer = mediaItem == null
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceMediumExtra))

                    Column(
                        modifier = Modifier
                            .padding(horizontal = spacing.spaceMedium)
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = stringResource(id = R.string.overview),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        Spacer(modifier = Modifier.height(spacing.spaceMicroSmall))

                        if (mediaItem == null) {
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(spacing.spaceMedium)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceContainer,
                                        shape = CircleShape
                                    )
                            )
                        } else {

                            Text(
                                text = mediaItem.overview,
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                            )

                        }


                       /* Spacer(modifier = Modifier.height(spacing.spaceSmall))

                        Text(
                            text = stringResource(
                                id = R.string.country, mediaItem?.originCountry ?: stringResource(
                                    id = R.string.no_data
                                )
                            ),
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                        )*/

                        Spacer(modifier = Modifier.height(spacing.spaceSmall))

                        Text(
                            text = stringResource(id = R.string.production),
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                        )
                        Spacer(modifier = Modifier.height(spacing.spaceMicroSmall))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(end = spacing.spaceMedium),
                            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
                        ) {

                            if (mediaItem == null) {

                            } else {
                                items(mediaItem.productionCompaniesDetail) { item: ProductionCompany ->

                                    ProductionCompanyItem(
                                        item = item,
                                        isLoadShimmer = mediaItem == null
                                    )
                                }
                            }


                        }
                    }

                }

            }

        }

        FavoriteItem(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .align(Alignment.TopEnd),
            onClickFavorite = {
                onClickFavorite()
            },
            isFavorite = mediaItem?.isFavorite ?: false, isLoadShimmer = mediaItem == null
        )

    }


}


@Composable
fun ProductionCompanyItem(
    modifier: Modifier = Modifier,
    item: ProductionCompany,
    isLoadShimmer: Boolean,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val logoImage = "${Constants.IMAGE_BASE_URL}${item.logoPath}"

    val imagePainterLogo =
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context).data(logoImage).size(
                Size.ORIGINAL
            ).build()
        )


    val imageLogoState = imagePainterLogo.state

    ElevatedCard(modifier = modifier.height(spacing.upcomingMovieItemHeight)) {

        Column(
            modifier = Modifier
                .padding(spacing.spaceMicroSmall)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (isLoadShimmer) {

                Box(
                    modifier = Modifier
                        .size(spacing.iconButtonSizeMedium)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(spacing.spaceMicroSmall))

                Column {
                    Box(
                        modifier = Modifier
                            .height(spacing.spaceMedium)
                            .width(spacing.spaceLargeExtra * 2)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onBackground)
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    Box(
                        modifier = Modifier
                            .height(spacing.spaceMedium)
                            .width(spacing.spaceLargeExtra * 2)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onBackground)
                    )

                }

            } else {
                if (imageLogoState is AsyncImagePainter.State.Error) {
                    Image(
                        modifier = Modifier
                            .size(spacing.iconButtonSizeMedium)
                            .clip(shape = CircleShape),
                        imageVector = Icons.Default.ImageNotSupported,
                        contentDescription = item.name,
                    )
                }

                if (imageLogoState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }

                if (imageLogoState is AsyncImagePainter.State.Success) {
                    AsyncImage(
                        modifier = Modifier
                            .size(spacing.iconButtonSizeMedium * 2),
                        model = imageLogoState.result.drawable.toBitmap(),
                        contentDescription = item.name, contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(spacing.spaceMicroSmall))

                Column {


                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    Text(
                        text = item.originCountry,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )


                }

            }


        }

    }
}


@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier, isFavorite: Boolean,
    isLoadShimmer: Boolean = false,
    onClickFavorite: () -> Unit,
) {

    val spacing = LocalSpacing.current

    val backgroundColor = if (isLoadShimmer) {
        Soft
    } else {
        Soft.copy(alpha = 0.72f)
    }

    ElevatedCard(modifier = modifier.size(spacing.iconButtonSizeSmall),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = { onClickFavorite() }) {

        val icon = if (isFavorite) {
            Icons.Default.Favorite
        } else {
            Icons.Outlined.FavoriteBorder
        }

        val colorIcon = if (isFavorite) {
            Color.Red
        } else {
            Color.White
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isLoadShimmer.not()) {
                Icon(

                    modifier = Modifier
                        .size(spacing.iconSize),
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.icon_favorite),
                    tint = colorIcon
                )

            }

        }


    }

}

@Composable
fun IconAndTextContainer(
    modifier: Modifier = Modifier,
    textContent: String, isItemNull: Boolean = true,
    icon: ImageVector = Icons.Default.Image,
    description: String,
) {

    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(horizontal = spacing.spaceMedium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (isItemNull) {
            Box(
                modifier = Modifier
                    .height(spacing.spaceMedium)
                    .width(spacing.spaceLargeExtraMax * 2)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape
                    )
            )
        } else {
            Icon(
                modifier = Modifier.size(spacing.iconSize),
                imageVector = icon,
                contentDescription = description
            )

            Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))

            Text(
                text = textContent,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )


        }

    }
}

@Composable
fun DetailOverlay(
    modifier: Modifier = Modifier,
    alpha: Float,
    color: Color,
    brush: Brush = Brush.verticalGradient(listOf(color.copy(alpha), color)),
    shape: Shape = RectangleShape,
) {
    Box(
        modifier = modifier
            .background(brush, shape)
            .fillMaxSize()
    )
}

@Preview
@Composable
fun DetailItemPreview() {
    DetailItem(onClickFavorite = {})
}