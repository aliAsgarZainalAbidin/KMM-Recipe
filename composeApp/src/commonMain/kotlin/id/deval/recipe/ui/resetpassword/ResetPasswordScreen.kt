package id.deval.recipe.ui.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.resetpassword.effect.ResetPasswordEffect
import id.deval.recipe.ui.resetpassword.event.ResetPasswordEvent
import id.deval.recipe.ui.resetpassword.state.ResetPasswordState
import id.deval.recipe.util.safeNavigate
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.baseline_check_24
import kmm_recipe.composeapp.generated.resources.done
import kmm_recipe.composeapp.generated.resources.enter_new_password
import kmm_recipe.composeapp.generated.resources.lock
import kmm_recipe.composeapp.generated.resources.password_hint
import kmm_recipe.composeapp.generated.resources.password_requirement_title
import kmm_recipe.composeapp.generated.resources.requirement_6_chars
import kmm_recipe.composeapp.generated.resources.requirement_number
import kmm_recipe.composeapp.generated.resources.reset_your_password
import kmm_recipe.composeapp.generated.resources.sign_up
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

@Composable
fun ResetPasswordScreen(
    navController : NavController
){
    val resetPasswordViewModel by appRecipeModule.instance<ResetPasswordViewModel>()
    val resetPasswordState by resetPasswordViewModel.resetPasswordState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        resetPasswordViewModel.resetPasswordEffect.collectLatest { effect ->
            when(effect){
                is ResetPasswordEffect.ShowToast -> {

                }
                is ResetPasswordEffect.NavigateToMain -> {
                    navController.safeNavigate(AppNavigation.Main.route)
                }
            }
        }
    }

    ResetPasswordContent(
        state = resetPasswordState,
        onEvent = resetPasswordViewModel::onEvent
    )
}

@Composable
fun ResetPasswordContent(
    state : ResetPasswordState,
    onEvent : (ResetPasswordEvent) -> Unit
){
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(Res.string.reset_your_password),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 55.dp)
            )
            Text(
                text = stringResource(Res.string.enter_new_password),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
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
                    onEvent(ResetPasswordEvent.OnPasswordChanged(it))
                },
                modifier = Modifier
                    .padding(top = 24.dp)
            )
            Text(
                text = stringResource(Res.string.password_requirement_title),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = mainTextColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(
                    painter = painterResource(Res.drawable.baseline_check_24),
                    contentDescription = "checklist",
                    tint = if(state.isPassAtleastSix) MaterialTheme.colorScheme.primary else secondaryTextColor
                )
                Text(
                    text = stringResource(Res.string.requirement_6_chars),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if(state.isPassAtleastSix) mainTextColor else secondaryTextColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(
                    painter = painterResource(Res.drawable.baseline_check_24),
                    contentDescription = "checklist",
                    tint = if(state.isPassContaintNumber) MaterialTheme.colorScheme.primary else secondaryTextColor
                )
                Text(
                    text = stringResource(Res.string.requirement_number),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if(state.isPassContaintNumber) mainTextColor else secondaryTextColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            RecipeButton.DefaultFilledButton(
                text = stringResource(Res.string.done),
                enabled = state.isEnabledButton,
                onClick = {
                    onEvent(ResetPasswordEvent.OnDoneClicked)
                },
            )
        }
    }
}