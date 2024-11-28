package id.deval.recipe.ui.otp

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
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.otp.effect.OtpScreenEffect
import id.deval.recipe.ui.otp.event.OtpScreenEvent
import id.deval.recipe.ui.otp.state.OtpScreenState
import id.deval.recipe.util.rules.AdaptiveLayoutRule
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.check_email
import kmm_recipe.composeapp.generated.resources.code_expires_in
import kmm_recipe.composeapp.generated.resources.email_code_sent
import kmm_recipe.composeapp.generated.resources.send_again
import kmm_recipe.composeapp.generated.resources.verify
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance


class OtpScreen : Screen {

    @Composable
    override fun Content() {
        val otpViewModel by appRecipeModule.instance<OtpViewModel>()
        val otpScreenState by otpViewModel.otpScreenState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            otpViewModel.otpScreenEffect.collectLatest { effect ->
                when (effect) {
                    is OtpScreenEffect.NavigateToMain -> {
                        navigator?.replaceAll(AppNavigation.Main.screen)
                    }

                    is OtpScreenEffect.ShowToast -> {

                    }

                    is OtpScreenEffect.OnResendOtp -> {

                    }

                    is OtpScreenEffect.NavigateToResetPassword -> {
                        navigator?.push(AppNavigation.ResetPassword.screen)
                    }
                }
            }
        }

        OtpScreenContent(
            otpScreenState,
            otpViewModel::onEvent
        )
    }

    @Composable
    fun OtpScreenContent(
        state: OtpScreenState,
        onEvent: (OtpScreenEvent) -> Unit
    ) {
        val customModifier = RecipeCommonUI.AdaptiveModifier(
            compactModifier = Modifier.fillMaxWidth(),
            mediumModifier = Modifier.widthIn(
                min = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp,
                max = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp,
            ),
            expandedModifier = Modifier.widthIn(
                min = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp,
                max = AdaptiveLayoutRule.LargeComponentWidth.mediumRule.dp,
            )
        )

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.check_email),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 19.dp)
                )
                Text(
                    text = stringResource(Res.string.email_code_sent),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = secondaryTextColor
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                RecipeTextField.OtpTextField(
                    value = state.otp,
                    modifier = customModifier.padding(
                        top = 32.dp
                    ),
                    onValueChange = {
                        onEvent(OtpScreenEvent.OnOtpChanged(it))
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(Res.string.code_expires_in),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = mainTextColor
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = state.time,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = secondaryColor
                        ),
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                RecipeButton.DefaultFilledButton(
                    onClick = {
                        onEvent(OtpScreenEvent.OnVerifyClicked(state.otp))
                    },
                    text = stringResource(Res.string.verify),
                    modifier = customModifier.padding(top = 32.dp),
                    enabled = state.isEnabledVerifyButton
                )
                RecipeButton.DefaultTextButton(
                    onClick = {},
                    text = stringResource(Res.string.send_again),
                    modifier = customModifier.padding(top = 16.dp),
                    enabled = state.isEnabledResendButton
                )
            }
        }
    }
}