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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import id.deval.recipe.shared.Greeting
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.theme.RecipeAppTheme
import id.deval.recipe.ui.forgotpassword.ForgotPasswordScreen
import id.deval.recipe.ui.login.LoginScreen
import id.deval.recipe.ui.main.MainScreen
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.otp.OtpScreen
import id.deval.recipe.ui.resetpassword.ResetPasswordScreen
import id.deval.recipe.ui.signup.SignUpScreen
import id.deval.recipe.ui.welcome.WelcomeScreen
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
                        LoginScreen()
                    )
                }
                AppNavigation.Main.route -> {
                    Navigator(
                        MainScreen()
                    )
                }

                AppNavigation.Welcome.route -> {
                    Navigator(
                        WelcomeScreen()
                    )
                }

                AppNavigation.SignUp.route -> {
                    Navigator(
                        SignUpScreen()
                    )
                }

                AppNavigation.Otp.route -> {
                    Navigator(
                        OtpScreen()
                    )
                }

                AppNavigation.ForgotPassword.route -> {
                    Navigator(
                        ForgotPasswordScreen()
                    )
                }

                AppNavigation.ResetPassword.route -> {
                    Navigator(
                        ResetPasswordScreen()
                    )
                }
            }
        }
    }
}

@Preview
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