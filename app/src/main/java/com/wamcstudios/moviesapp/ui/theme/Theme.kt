package com.wamcstudios.moviesapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.wamcstudios.aifusion.core.desingsystem.theme.CompactDimensions
import com.wamcstudios.aifusion.core.desingsystem.theme.CompactTypography
import com.wamcstudios.aifusion.core.desingsystem.theme.ExpandedDimensions
import com.wamcstudios.aifusion.core.desingsystem.theme.ExpandedTypography
import com.wamcstudios.aifusion.core.desingsystem.theme.MediumDimensions
import com.wamcstudios.aifusion.core.desingsystem.theme.MediumTypography
import com.wamcstudios.aifusion.core.desingsystem.theme.ProvideAppUtils
import com.wamcstudios.aifusion.core.desingsystem.theme.WindowInfo
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_background
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_error
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_errorContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_inverseOnSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_inversePrimary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_inverseSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onBackground
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onError
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onErrorContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onPrimary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onPrimaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onSecondary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onSecondaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onSurfaceVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onTertiary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_onTertiaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_outline
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_outlineVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_primary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_primaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_scrim
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_secondary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_secondaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_surface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_surfaceTint
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_surfaceVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_tertiary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_dark_tertiaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_background
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_error
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_errorContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_inverseOnSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_inversePrimary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_inverseSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onBackground
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onError
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onErrorContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onPrimary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onPrimaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onSecondary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onSecondaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onSurface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onSurfaceVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onTertiary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_onTertiaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_outline
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_outlineVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_primary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_primaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_scrim
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_secondary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_secondaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_surface
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_surfaceTint
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_surfaceVariant
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_tertiary
import com.wamcstudios.aifusion.core.desingsystem.theme.md_theme_light_tertiaryContainer
import com.wamcstudios.aifusion.core.desingsystem.theme.rememberWindowInfo

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

@Composable
fun MoviesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme

        }
    }

    val windowInfo = rememberWindowInfo()

    var typography = CompactTypography
    var appDimens = CompactDimensions


    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        appDimens = CompactDimensions
        typography = CompactTypography

    } else if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {
        appDimens = MediumDimensions
        typography = MediumTypography

    } else {

        appDimens = ExpandedDimensions
        typography = ExpandedTypography

    }


    // Proveemos las Dimenciones para poder usarlas en toda la app
    ProvideAppUtils(appDimens = appDimens) {

        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}