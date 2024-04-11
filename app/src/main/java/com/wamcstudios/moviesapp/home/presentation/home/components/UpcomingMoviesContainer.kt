package com.wamcstudios.moviesapp.home.presentation.home.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.ui.components.DefaultHorizontalPagerIndicator
import com.wamcstudios.moviesapp.core.ui.components.MoviesAndTvSeriesContainer
import com.wamcstudios.moviesapp.core.ui.components.UpcomingMovieItemShimmer
import com.wamcstudios.moviesapp.core.ui.components.pagerTransition
import com.wamcstudios.moviesapp.core.utils.toDateString
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UpcomingMoviesContainer(
    modifier: Modifier = Modifier,
    mediaMovieList: List<Media>,
    onSeeClick: () -> Unit, onMovieClick: (Media) -> Unit,
) {

    val spacing = LocalSpacing.current
    val pageCount = if (mediaMovieList.isEmpty()){
        10
    }else{
        mediaMovieList.size
    }

    val pagerState = rememberPagerState(pageCount = { pageCount })

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(3000)
            if (pagerState.canScrollForward) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            } else {
                pagerState.animateScrollToPage(0)
            }
        }

    }


    MoviesAndTvSeriesContainer(
        modifier = modifier,
        title = stringResource(id = R.string.upcoming_movies),
        onSeeAllClick = onSeeClick
    ) {

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = spacing.spaceLargeMedium)
        ) { page: Int ->



            if (mediaMovieList.isEmpty()){
                UpcomingMovieItemShimmer(modifier = Modifier.pagerTransition(pagerState, page))
            }else{

                val item = mediaMovieList[page]
                UpcomingMovieItem(modifier = Modifier.pagerTransition(pagerState, page),
                    title = item.title,
                    image = item.backdropPath,
                    releaseData = item.releaseDate.toDateString(),
                    onClick = { onMovieClick(item) })
            }


        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        DefaultHorizontalPagerIndicator(
            modifier = Modifier
                .padding(horizontal = spacing.spaceMediumExtra)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            pageCount = pageCount
        )


    }
}

@Composable
fun UpcomingMovieItem(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
    releaseData: String, onClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val imageUrl = "${Constants.IMAGE_BASE_URL}${image}"
    Log.d("imageUrl", "$title: $imageUrl")

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    )

    val imageState = imagePainter.state



    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(spacing.upcomingMovieItemHeight),
        shape = RoundedCornerShape(spacing.spaceMedium),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (imageState is AsyncImagePainter.State.Success) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = imageState.result.drawable.toBitmap(),
                    contentDescription = title, contentScale = ContentScale.Crop,
                )

            }

            if (imageState is AsyncImagePainter.State.Error) {

                Icon(
                    modifier = Modifier
                        .size(spacing.iconButtonSizeExtra + spacing.iconButtonSizeExtra)
                        .align(Alignment.Center),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = title, tint = MaterialTheme.colorScheme.onBackground
                )
            }


            if (imageState is AsyncImagePainter.State.Loading) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {


                Surface(
                    modifier = Modifier.padding(end = spacing.spaceMediumExtra),
                    shape = CircleShape,
                    color = Color.DarkGray.copy(alpha = 0.5f)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(spacing.spaceNanoSmall),
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Start
                    )
                }


                Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                Surface(
                    modifier = Modifier.padding(end = spacing.spaceMediumExtra),
                    shape = CircleShape,
                    color = Color.DarkGray.copy(alpha = 0.5f)
                ) {
                    Text(
                        modifier = Modifier.padding(spacing.spaceNanoSmall),
                        text = stringResource(
                            id = R.string.upcoming_movie_release_date_text,
                            releaseData,
                        ), color = Color.White
                    )
                }
            }


        }
    }

}