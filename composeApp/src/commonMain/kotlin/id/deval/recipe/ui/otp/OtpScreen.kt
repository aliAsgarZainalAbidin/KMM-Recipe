package id.deval.recipe.ui.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.otp.effect.OtpScreenEffect
import id.deval.recipe.ui.otp.event.OtpScreenEvent
import id.deval.recipe.ui.otp.state.OtpScreenState
import id.deval.recipe.util.safeNavigate
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.check_email
import kmm_recipe.composeapp.generated.resources.code_expires_in
import kmm_recipe.composeapp.generated.resources.email_code_sent
import kmm_recipe.composeapp.generated.resources.send_again
import kmm_recipe.composeapp.generated.resources.verify
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

@Composable
fun OtpScreen(
    navController: NavController
) {
    val otpViewModel by appRecipeModule.instance<OtpViewModel>()
    val otpScreenState by otpViewModel.otpScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        otpViewModel.otpScreenEffect.collectLatest { effect ->
            when (effect) {
                is OtpScreenEffect.NavigateToMain -> {
                    navController.safeNavigate(AppNavigation.Main.route)
                }

                is OtpScreenEffect.ShowToast -> {

                }

                is OtpScreenEffect.OnResendOtp -> {

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
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.padding(
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
                onClick = {},
                text = stringResource(Res.string.verify),
                modifier = Modifier.padding(top = 32.dp),
                enabled = state.isEnabledVerifyButton
            )
            RecipeButton.DefaultTextButton(
                onClick = {},
                text = stringResource(Res.string.send_again),
                modifier = Modifier.padding(top = 16.dp),
                enabled = state.isEnabledResendButton
            )
        }
    }
}