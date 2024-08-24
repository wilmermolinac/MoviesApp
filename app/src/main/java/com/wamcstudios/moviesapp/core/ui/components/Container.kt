package com.wamcstudios.moviesapp.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing

@Composable
fun ContainerTitleWithButton(
    modifier: Modifier = Modifier,
    title: String,
    onSeeAllClick: () -> Unit,
) {

    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(horizontal = spacing.spaceMedium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )
        )

        TextButton(onClick = { onSeeAllClick() }) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }


    }
}


@Composable
fun MoviesAndTvSeriesContainer(
    modifier: Modifier = Modifier,
    title: String,
    onSeeAllClick: () -> Unit, content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = LocalSpacing.current
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        ContainerTitleWithButton(title = title, onSeeAllClick = onSeeAllClick)
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        content()
    }

}


@Preview
@Composable
fun ContainerTitleWithButtonPreview() {
    ContainerTitleWithButton(title = "Upcoming Movies", onSeeAllClick = {})
}