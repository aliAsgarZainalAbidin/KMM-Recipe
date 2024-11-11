package id.deval.recipe.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.forgotpassword.effect.ForgotPasswordEffect
import id.deval.recipe.ui.forgotpassword.event.ForgotPasswordEvent
import id.deval.recipe.ui.forgotpassword.state.ForgotPasswordState
import id.deval.recipe.ui.navigation.AppNavigation
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.email_phone_hint
import kmm_recipe.composeapp.generated.resources.enter_email_recovery
import kmm_recipe.composeapp.generated.resources.message
import kmm_recipe.composeapp.generated.resources.password_recovery
import kmm_recipe.composeapp.generated.resources.sign_in
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val forgotPasswordViewModel by appRecipeModule.instance<ForgotPasswordViewModel>()
        val forgotPasswordState by forgotPasswordViewModel.forgotPasswordState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            forgotPasswordViewModel.forgotPasswordEffect.collectLatest { effect ->
                when (effect) {
                    is ForgotPasswordEffect.NavigateToOtp -> {
                        navigator?.push(AppNavigation.Otp.screen)
                    }
                }
            }
        }

        ForgotPasswordContent(
            forgotPasswordState,
            forgotPasswordViewModel::onEvent
        )
    }

    @Composable
    fun ForgotPasswordContent(
        state: ForgotPasswordState,
        onEvent: (ForgotPasswordEvent) -> Unit
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.password_recovery),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 83.dp)
                )
                Text(
                    text = stringResource(Res.string.enter_email_recovery),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                RecipeTextField.Outlined(
                    value = state.email,
                    onValueChange = {
                        onEvent(ForgotPasswordEvent.OnEmailChanged(it))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.email_phone_hint),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    prefix = {
                        Icon(
                            painter = painterResource(Res.drawable.message),
                            contentDescription = "email icon"
                        )
                    },
                    modifier = Modifier.padding(top = 32.dp)
                )
                Spacer(
                    modifier = Modifier.weight(1.0f)
                )
                RecipeButton.DefaultFilledButton(
                    onClick = {
                        onEvent(ForgotPasswordEvent.SignInClicked)
                    },
                    text = stringResource(Res.string.sign_in),
                    enabled = state.isEnabledSignIn
                )
            }
        }
    }
}