package id.deval.recipe.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.deval.recipe.components.RecipeButton
//import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.typography
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.welcome.effect.WelcomeScreenEffect
import id.deval.recipe.ui.welcome.state.WelcomeScreenState
import id.deval.recipe.util.safeNavigate
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.get_started
import kmm_recipe.composeapp.generated.resources.welcome
import kmm_recipe.composeapp.generated.resources.welcome_subtitle
import kmm_recipe.composeapp.generated.resources.welcome_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.newInstance

@Composable
fun WelcomeScreen(
    navController: NavController = rememberNavController()
) {
//    val welcomeScreenViewModel by appRecipeModule.newInstance { WelcomeViewModel() }
//    val welcomeScreenState by welcomeScreenViewModel.welcomeScreenState.collectAsState()

//    LaunchedEffect(Unit) {
//        welcomeScreenViewModel.welcomeScreenEffect.collect { latestEffect ->
//            when (latestEffect) {
//                is WelcomeScreenEffect.NavigateToMain -> {
//                    navController.safeNavigate(AppNavigation.Main.route)
//                }
//
//                is WelcomeScreenEffect.NavigateToLogin -> {
//                    navController.safeNavigate(AppNavigation.Login.route)
//                }
//            }
//        }
//    }

    WelcomeScreenContent()
}

@Composable
fun WelcomeScreenContent(
//    state: WelcomeScreenState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 72.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.Onboarding),
                contentDescription = "onboarding image",
                modifier = Modifier.size(width = 420.dp, height = 406.dp),
            )
            Text(
                text = stringResource(Res.string.welcome_title),
                modifier = Modifier.padding(
                    top = 48.dp
                ),
                style = typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(Res.string.welcome_subtitle),
                modifier = Modifier.padding(
                    top = 16.dp
                ),
                style = typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            RecipeButton.DefaultFilledButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 24.dp, end = 24.dp),
                text = stringResource(Res.string.get_started),
                textStyle = typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}