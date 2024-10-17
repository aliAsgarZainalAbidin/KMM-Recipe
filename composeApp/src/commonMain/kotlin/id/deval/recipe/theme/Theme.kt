package id.deval.recipe.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val darkColorPalette = darkColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    onPrimary = white,
    onSecondary = secondaryColor,
    onError = white,
    error = secondaryColor,
    surface = white,
    onSurface = secondaryText
)

private val lightColorPalette = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    onPrimary = white,
    onSecondary = secondaryColor,
    onError = white,
    error = secondaryColor,
    surface = white,
    onSurface = secondaryText
)

@Composable
fun RecipeAppTheme(
    darkTheme : Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit
){
    val colors = if (darkTheme){
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}