package com.wamcstudios.moviesapp.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = spacing.spaceLargeMedium,
                bottom = spacing.spaceLargeMedium,
                start = spacing.spaceMedium,
                end = spacing.spaceMedium
            )
        ) {

            item {

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = stringResource(id = R.string.about),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    ElevatedCard(onClick = { }) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = spacing.spaceSmall)
                                .height(spacing.topAppBarHeight)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(spacing.iconSize),
                                    painter = painterResource(id = R.drawable.ic_github),
                                    contentDescription = stringResource(id = R.string.icon_github)
                                )

                                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                                Text(
                                    text = stringResource(id = R.string.source_code_github),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                            }


                            Icon(
                                modifier = Modifier.size(spacing.iconSize),
                                imageVector = Icons.AutoMirrored.Default.NavigateNext,
                                contentDescription = null, tint = MaterialTheme.colorScheme.primary
                            )


                        }
                    }

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    ElevatedCard(onClick = { }) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = spacing.spaceSmall)
                                .height(spacing.topAppBarHeight)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(spacing.iconSize),
                                    imageVector = Icons.Default.PrivacyTip,
                                    contentDescription = stringResource(id = R.string.icon_privacy)
                                )

                                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                                Text(
                                    text = stringResource(id = R.string.privacy_policy),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                            }


                            Icon(
                                modifier = Modifier.size(spacing.iconSize),
                                imageVector = Icons.AutoMirrored.Default.NavigateNext,
                                contentDescription = null, tint = MaterialTheme.colorScheme.primary
                            )


                        }
                    }

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    ElevatedCard() {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = spacing.spaceSmall)
                                .height(spacing.topAppBarHeight)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(spacing.iconSize),
                                    imageVector = Icons.Default.Info,
                                    contentDescription = stringResource(id = R.string.icon_info)
                                )

                                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                                Text(
                                    text = stringResource(id = R.string.version),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                            }


                            val versionCode = context.packageManager.getPackageInfo(
                                context.packageName,
                                0
                            ).versionName


                            Text(
                                text = versionCode,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold)
                            )


                        }
                    }


                }
            }

        }

    }
}

@Preview
@Composable
fun SettingsContentPreview() {
    SettingsContent()
}