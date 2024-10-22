package id.deval.recipe.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.navigation.Navigation
import id.deval.recipe.ui.welcome.effect.WelcomeScreenEffect
import id.deval.recipe.ui.welcome.event.WelcomeScreenEvent
import id.deval.recipe.ui.welcome.state.WelcomeScreenState
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.get_started
import kmm_recipe.composeapp.generated.resources.welcome_subtitle
import kmm_recipe.composeapp.generated.resources.welcome_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class WelcomeScreen : Screen {

    @Composable
    override fun Content() {
        val welcomeScreenViewModel by appRecipeModule.instance<WelcomeViewModel>()
        val welcomeScreenState by welcomeScreenViewModel.welcomeScreenState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            welcomeScreenViewModel.welcomeScreenEffect.collect { latestEffect ->
                when (latestEffect) {
                    is WelcomeScreenEffect.NavigateToMain -> {
                        navigator.replaceAll(AppNavigation.Main.screen)
                    }

                    is WelcomeScreenEffect.NavigateToLogin -> {
                        navigator.replaceAll(AppNavigation.Login.screen)
                    }
                }
            }
        }

        WelcomeScreenContent(
            welcomeScreenState,
            welcomeScreenViewModel::onEvent
        )
    }


    @Composable
    fun WelcomeScreenContent(
        state: WelcomeScreenState,
        onEvent : (WelcomeScreenEvent) -> Unit = {}
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
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(Res.string.welcome_subtitle),
                    modifier = Modifier.padding(
                        top = 16.dp
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier.weight(1.0f)
                )
                RecipeButton.DefaultFilledButton(
                    onClick = {
                        onEvent(WelcomeScreenEvent.OnGetStartedClicked(false))
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    text = stringResource(Res.string.get_started),
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}