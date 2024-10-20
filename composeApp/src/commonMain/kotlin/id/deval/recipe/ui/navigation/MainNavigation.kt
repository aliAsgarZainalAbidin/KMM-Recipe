package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class MainNavigation(
    override val route : String,
    val NavArguments : List<NamedNavArgument>
) : Navigation {
    data object Home : MainNavigation("home", emptyList())
    data object Upload : MainNavigation("upload", emptyList())
    data object Scan : MainNavigation("scan", emptyList())
    data object Notification : MainNavigation("notification", emptyList())
    data object Profile : MainNavigation("profile", emptyList())
}