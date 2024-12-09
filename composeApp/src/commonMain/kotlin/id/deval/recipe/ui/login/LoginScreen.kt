package id.deval.recipe.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.DefaultRedFilledButtonStyle
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.ui.login.effect.LoginScreenEffect
import id.deval.recipe.ui.login.event.LoginScreenEvent
import id.deval.recipe.ui.login.state.LoginScreenState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.util.rules.AdaptiveLayoutRule
import kmm_recipe.composeapp.generated.resources.Google
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.dont_have_account
import kmm_recipe.composeapp.generated.resources.email_phone_hint
import kmm_recipe.composeapp.generated.resources.enter_account
import kmm_recipe.composeapp.generated.resources.forgot_password
import kmm_recipe.composeapp.generated.resources.google
import kmm_recipe.composeapp.generated.resources.lock
import kmm_recipe.composeapp.generated.resources.login
import kmm_recipe.composeapp.generated.resources.message
import kmm_recipe.composeapp.generated.resources.or_with_google
import kmm_recipe.composeapp.generated.resources.password_hint
import kmm_recipe.composeapp.generated.resources.sign_up
import kmm_recipe.composeapp.generated.resources.welcome_back
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val loginViewModel by appRecipeModule.instance<LoginViewModel>()
        val loginScreenState by loginViewModel.loginScreenState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            loginViewModel.loginScreenEffect.collectLatest { effect ->
                loginViewModel.onEffect(effect, navigator)
            }
        }

        LoginScreenContent(
            loginScreenState,
            loginViewModel::onEvent
        )
    }

    @Composable
    fun LoginScreenContent(
        state: LoginScreenState,
        onEvent: (LoginScreenEvent) -> Unit = {}
    ) {
        val customModifier = RecipeCommonUI.AdaptiveModifier(
            compactModifier = Modifier.fillMaxWidth(),
            mediumModifier = Modifier.widthIn(
                max = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp,
                min = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp
            ),
            expandedModifier = Modifier.widthIn(
                max = AdaptiveLayoutRule.LargeComponentWidth.expandedRule.dp,
                min = AdaptiveLayoutRule.LargeComponentWidth.expandedRule.dp
            )
        )

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.welcome_back),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 107.dp)
                )
                Text(
                    text = stringResource(Res.string.enter_account),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                RecipeTextField.Outlined(
                    value = state.email,
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
                    onValueChange = {
                        onEvent(LoginScreenEvent.OnEmailChanged(it))
                    },
                    modifier = customModifier
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp)
                )
                RecipeTextField.Outlined(
                    value = state.password,
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.password_hint),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    prefix = {
                        Icon(
                            painter = painterResource(Res.drawable.lock),
                            contentDescription = "password icon"
                        )
                    },
                    onValueChange = {
                        onEvent(LoginScreenEvent.OnPasswordChanged(it))
                    },
                    modifier = customModifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                )
                Row(
                    modifier = customModifier
                ) {
                    Spacer(
                        modifier = Modifier.weight(1.0f)
                    )
                    Text(
                        text = stringResource(Res.string.forgot_password),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = mainTextColor
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 24.dp, top = 24.dp)
                            .clickable {
                                onEvent(LoginScreenEvent.OnForgotPasswordClicked)
                            }
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1.0f)
                )
                RecipeButton.DefaultFilledButton(
                    onClick = {
                        onEvent(LoginScreenEvent.OnLoginClicked(state.email, state.password))
                    },
                    modifier = customModifier
                        .padding(start = 24.dp, end = 24.dp, top = 12.dp),
                    text = stringResource(Res.string.login),
                )
                Text(
                    text = stringResource(Res.string.or_with_google),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 24.dp)
                )
                RecipeButton.DefaultFilledButton(
                    onClick = {},
                    modifier = customModifier
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                    text = stringResource(Res.string.Google),
                    startIcon = painterResource(Res.drawable.google),
                    color = DefaultRedFilledButtonStyle()
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 24.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(Res.string.dont_have_account),
                        style = MaterialTheme.typography.bodyMedium
                            .copy(color = mainTextColor),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = stringResource(Res.string.sign_up),
                        style = MaterialTheme.typography.bodyMedium
                            .copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                        modifier = Modifier.clickable {
                            onEvent(LoginScreenEvent.OnSignUpClicked)
                        }
                    )
                }
            }
        }
    }
}