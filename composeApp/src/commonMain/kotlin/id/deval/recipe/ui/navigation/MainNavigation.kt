package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.ui.home.HomeScreen
import id.deval.recipe.ui.notif.NotificationScreen
import id.deval.recipe.ui.profile.ProfileScreen
import id.deval.recipe.ui.scan.ScanScreen
import id.deval.recipe.ui.upload.UploadScreenFirstStep
import id.deval.recipe.ui.upload.UploadScreenSecondStep
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
    override val screen: Screen,
    val title: String,
    val icon: DrawableResource
) : Navigation {
    data object Home : MainNavigation("home", emptyList(), HomeScreen(),"Home", Res.drawable.home)
    data object Upload : MainNavigation("upload", emptyList(), UploadScreenFirstStep(),"Upload", Res.drawable.edit)
    data object UploadSecond : MainNavigation("uploadSecond", emptyList(), UploadScreenSecondStep(),"Upload", Res.drawable.edit)
    data object Scan : MainNavigation("scan", emptyList(), ScanScreen(),"Scan", Res.drawable.scan)
    data object Notification :
        MainNavigation("notification", emptyList(), NotificationScreen(),"Notification", Res.drawable.notification)
    data object Profile : MainNavigation("profile", emptyList(), ProfileScreen(),"Profile", Res.drawable.profile)
}