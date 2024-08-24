package com.wamcstudios.moviesapp.search.presentation.components

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    queryValue: String,
    onChangeQuery: (String) -> Unit, focusManager: FocusManager,
) {
    val spacing = LocalSpacing.current

    var isFocused by remember {
        mutableStateOf(false)
    }

    val backgroundColor = if (isFocused) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceContainer
    }

    val textColor = if (isFocused) {
        Color.WHITE
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    Box(
        modifier = modifier
            .padding(horizontal = spacing.spaceSmall)
            .fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent {

                    isFocused = it.isFocused
                },
            value = queryValue,
            onValueChange = { onChangeQuery(it) },
            shape = CircleShape,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false, keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {

                focusManager.clearFocus()

            }),
            trailingIcon = {
                if (queryValue.isBlank()) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                } else {
                    IconButton(onClick = {
                        onChangeQuery("")
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear, contentDescription = stringResource(
                                id = R.string.clear
                            )
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = androidx.compose.ui.graphics.Color.White,
                unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                cursorColor = androidx.compose.ui.graphics.Color.White
            )
        )


    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(
        queryValue = "Hello", onChangeQuery = {}, focusManager = LocalFocusManager.current
    )
}