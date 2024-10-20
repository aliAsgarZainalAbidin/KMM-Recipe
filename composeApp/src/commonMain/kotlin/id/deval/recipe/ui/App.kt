package id.deval.recipe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.Navigator
import id.deval.recipe.shared.Greeting
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.theme.RecipeAppTheme
import id.deval.recipe.ui.forgotpassword.ForgotPasswordScreenNavigator
import id.deval.recipe.ui.login.LoginScreenNavigator
import id.deval.recipe.ui.main.MainScreenNavigator
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.otp.OtpScreenNavigator
import id.deval.recipe.ui.resetpassword.ResetPasswordScreenNavigator
import id.deval.recipe.ui.signup.SignUpScreenNavigator
import id.deval.recipe.ui.welcome.WelcomeScreenNavigator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.category
import kmm_recipe.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {

    var currentScreen by remember { mutableStateOf(AppNavigation.Welcome.route) }
    RecipeAppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            /*TODO()
            *   startDestination base on already login or not
            *   please make DataStorage or SharedPreference account to check
            * */
//            NavHost(
//                navController = navigator,
//                startDestination = AppNavigation.Main.route,
//                modifier = Modifier.fillMaxSize()
//            ){
//                composable(AppNavigation.Splash.route){
//
//                }
//                composable(AppNavigation.Login.route){
//                    LoginScreen(navigator)
//                }
//                composable(AppNavigation.Main.route){
//                    SampleCompose()
//                }
//                composable(AppNavigation.Welcome.route){
//                    WelcomeScreen(navigator)
//                }
//                composable(AppNavigation.SignUp.route){
//                    SignupScreen(navigator)
//                }
//                composable(AppNavigation.Otp.route){
//                    OtpScreen(navigator)
//                }
//                composable(AppNavigation.ForgotPassword.route){
//                    ForgotPasswordScreen(navigator)
//                }
//                composable(AppNavigation.ResetPassword.route){
//                    ResetPasswordScreen(navigator)
//                }
//            }
            when (currentScreen) {
                AppNavigation.Splash.route -> {}
                AppNavigation.Login.route -> {
                    Navigator(
                        LoginScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }
                AppNavigation.Main.route -> {
                    Navigator(
                        MainScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }

                AppNavigation.Welcome.route -> {
                    Navigator(
                        WelcomeScreenNavigator(
                            navigate = {
                                currentScreen = it.route
                            }
                        )
                    )
                }

                AppNavigation.SignUp.route -> {
                    Navigator(
                        SignUpScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }

                AppNavigation.Otp.route -> {
                    Navigator(
                        OtpScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }

                AppNavigation.ForgotPassword.route -> {
                    Navigator(
                        ForgotPasswordScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }

                AppNavigation.ResetPassword.route -> {
                    Navigator(
                        ResetPasswordScreenNavigator{
                            currentScreen = it.route
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SampleCompose() {
    var showContent by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        RecipeButton.DefaultFilledButton(
            onClick = { showContent = !showContent },
            text = "Click me",
            startIcon = painterResource(Res.drawable.category),
//                color = DefaultRedFilledButtonStyle()
        )
        RecipeButton.DefaultOutlinedButton(
            onClick = { showContent = !showContent },
            text = "Click me 1",
            startIcon = painterResource(Res.drawable.category),
//                color = DefaultRedOutlineButtonStyle()
        )
        RecipeButton.DefaultTextButton(
            onClick = { showContent = !showContent },
            text = "Click me 2",
            startIcon = painterResource(Res.drawable.category),
            endIcon = painterResource(Res.drawable.category),
//                color = DefaultRedTextButtonStyle()
        )
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}