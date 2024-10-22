package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.edit
import kmm_recipe.composeapp.generated.resources.home
import kmm_recipe.composeapp.generated.resources.notification
import kmm_recipe.composeapp.generated.resources.profile
import kmm_recipe.composeapp.generated.resources.scan
import org.jetbrains.compose.resources.DrawableResource

sealed class MainNavigation(
    override val route: String,
    override val navArguments: List<NamedNavArgument>,
    val title: String,
    val icon: DrawableResource
) : Navigation {
    data object Home : MainNavigation("home", emptyList(), "Home", Res.drawable.home)
    data object Upload : MainNavigation("upload", emptyList(), "Upload", Res.drawable.edit)
    data object Scan : MainNavigation("scan", emptyList(), "Scan", Res.drawable.scan)
    data object Notification :
        MainNavigation("notification", emptyList(), "Notification", Res.drawable.notification)
    data object Profile : MainNavigation("profile", emptyList(), "Profile", Res.drawable.profile)
}