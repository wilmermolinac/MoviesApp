package com.wamcstudios.moviesapp.settings.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.settings.presentation.components.SettingsContent

@Composable
fun SettingScreen(onNavigate:(UiEvent) -> Unit) {

   Scaffold {
       SettingsContent(modifier = Modifier.padding(paddingValues = it))
   }

}