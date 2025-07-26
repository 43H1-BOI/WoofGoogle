package com.the43h1.woofgoogle.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81D8B9), // Primary (Dark)
    onPrimary = Color(0xFF12372C), // On Primary (Dark)
    primaryContainer = Color(0xFF275043), // Primary Container (Dark)
    onPrimaryContainer = Color(0xFF9BFEBB), // On Primary Container (Dark)

    secondary = Color(0xFF9CB5AA), // Secondary (Dark)
    onSecondary = Color(0xFF2B3A32), // On Secondary (Dark)
    secondaryContainer = Color(0xFF46584E), // Secondary Container (Dark)
    onSecondaryContainer = Color(0xFFD7E6DD), // On Secondary Container (Dark)

    tertiary = Color(0xFFAFC9DB), // Tertiary (Dark)
    onTertiary = Color(0xFF1E3743), // On Tertiary (Dark)
    tertiaryContainer = Color(0xFF324D5F), // Tertiary Container (Dark)
    onTertiaryContainer = Color(0xFFE2F0FA), // On Tertiary Container (Dark)

    error = Color(0xFFF0BAB4), // Error (Dark)
    onError = Color(0xFF670608), // On Error (Dark)
    errorContainer = Color(0xFFBA1B1B), // Error Container (Dark)
    onErrorContainer = Color(0xFFFFDAD5), // On Error Container (Dark)

    background = Color(0xFF151A16), // Background (Dark)
    onBackground = Color(0xFFECEFEA), // On Background (Dark)

    surface = Color(0xFF1A1C1A), // Surface (Dark)
    onSurface = Color(0xFFDCE0DB), // On Surface (Dark)

    outline = Color(0xFF8C9B92), // Outline (Dark)
    surfaceVariant = Color(0xFF46584E), // Surface-Variant (Dark)
    onSurfaceVariant = Color(0xFFD7E6DD) // On Surface-Variant (Dark)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF337A5C), // Primary (Light)
    onPrimary = Color(0xFFFFFFFF), // On Primary (Light)
    primaryContainer = Color(0xFF9BFEBB), // Primary Container (Light)
    onPrimaryContainer = Color(0xFF002116), // On Primary Container (Light)

    secondary = Color(0xFF526D5A), // Secondary (Light)
    onSecondary = Color(0xFFFFFFFF), // On Secondary (Light)
    secondaryContainer = Color(0xFFD7E6DD), // Secondary Container (Light)
    onSecondaryContainer = Color(0xFF122017), // On Secondary Container (Light)

    tertiary = Color(0xFF485C6E), // Tertiary (Light)
    onTertiary = Color(0xFFFFFFFF), // On Tertiary (Light)
    tertiaryContainer = Color(0xFFE2F0FA), // Tertiary Container (Light)
    onTertiaryContainer = Color(0xFF091F2A), // On Tertiary Container (Light)

    error = Color(0xFFB3261E), // Error (Light)
    onError = Color(0xFFFFFFFF), // On Error (Light)
    errorContainer = Color(0xFFFFDAD5), // Error Container (Light)
    onErrorContainer = Color(0xFF410002), // On Error Container (Light)

    background = Color(0xFFF8FFF6), // Background (Light)
    onBackground = Color(0xFF151A16), // On Background (Light)

    surface = Color(0xFFF8FFF6), // Surface (Light)
    onSurface = Color(0xFF151A16), // On Surface (Light)

    outline = Color(0xFF8C9B92), // Outline (Light)
    surfaceVariant = Color(0xFFD7E6DD), // Surface-Variant (Light)
    onSurfaceVariant = Color(0xFF46584E) // On Surface-Variant (Light)
)

@Composable
fun WoofGoogleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}