package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.ui.forgotpassword.ForgotPasswordScreen
import id.deval.recipe.ui.login.LoginScreen
import id.deval.recipe.ui.main.MainScreen
import id.deval.recipe.ui.otp.OtpScreen
import id.deval.recipe.ui.detail.RecipeDetailScreen
import id.deval.recipe.ui.resetpassword.ResetPasswordScreen
import id.deval.recipe.ui.signup.SignUpScreen
import id.deval.recipe.ui.welcome.WelcomeScreen

sealed class AppNavigation(
    override val route : String,
    override val navArguments : List<NamedNavArgument>,
    override val screen : Screen
) : Navigation {
    data object Splash : AppNavigation("splash", emptyList(), LoginScreen())
    data object Login : AppNavigation("login", emptyList(), LoginScreen())
    data object Main : AppNavigation("main", emptyList(), MainScreen())
    data object Welcome : AppNavigation("welcome", emptyList(), WelcomeScreen())
    data object SignUp : AppNavigation("signup", emptyList(), SignUpScreen())
    data object Otp : AppNavigation("otp", emptyList(), OtpScreen())
    data object ForgotPassword : AppNavigation("forgot_password", emptyList(), ForgotPasswordScreen())
    data object ResetPassword : AppNavigation("reset_password", emptyList(), ResetPasswordScreen())
    data object RecipeDetail : AppNavigation("recipe_detail", emptyList(), RecipeDetailScreen())
}