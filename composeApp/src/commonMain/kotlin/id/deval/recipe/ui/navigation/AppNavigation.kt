package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class AppNavigation(
    val route : String,
    val arguments : List<NamedNavArgument>
){
    data object Splash : AppNavigation("splash", emptyList())
    data object Login : AppNavigation("login", emptyList())
    data object Main : AppNavigation("main", emptyList())
    data object Welcome : AppNavigation("welcome", emptyList())
    data object SignUp : AppNavigation("signup", emptyList())
    data object Otp : AppNavigation("otp", emptyList())
    data object ForgotPassword : AppNavigation("forgot_password", emptyList())
    data object ResetPassword : AppNavigation("reset_password", emptyList())
}