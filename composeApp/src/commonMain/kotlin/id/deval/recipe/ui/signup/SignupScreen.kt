package id.deval.recipe.ui.signup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.form
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.login.event.LoginScreenEvent
import id.deval.recipe.ui.login.state.LoginScreenState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.navigation.Navigation
import id.deval.recipe.ui.signup.effect.SignupScreenEffect
import id.deval.recipe.ui.signup.event.SignupScreenEvent
import id.deval.recipe.ui.signup.state.SignupScreenState
import id.deval.recipe.util.safeNavigate
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.baseline_check_24
import kmm_recipe.composeapp.generated.resources.email_phone_hint
import kmm_recipe.composeapp.generated.resources.enter_account
import kmm_recipe.composeapp.generated.resources.lock
import kmm_recipe.composeapp.generated.resources.message
import kmm_recipe.composeapp.generated.resources.password_hint
import kmm_recipe.composeapp.generated.resources.password_requirement_title
import kmm_recipe.composeapp.generated.resources.requirement_6_chars
import kmm_recipe.composeapp.generated.resources.requirement_number
import kmm_recipe.composeapp.generated.resources.sign_up
import kmm_recipe.composeapp.generated.resources.welcome
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

data class SignUpScreenNavigator(
    val navigate : (Navigation) -> Unit
) : Screen{

    @Composable
    override fun Content() {
        val signupViewModel by appRecipeModule.instance<SignupViewModel>()
        val state = signupViewModel.signupScreenState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit){
            signupViewModel.signupScreenEffect.collectLatest { effect ->
                when(effect) {
                    is SignupScreenEffect.ShowToast -> {

                    }
                    is SignupScreenEffect.NavigateToOtp -> {
                        navigate(AppNavigation.Otp)
                    }
                }
            }
        }

        SingupScreenContent(
            state = state.value,
            onEvent = signupViewModel::onEvent
        )
    }

    @Composable
    fun SingupScreenContent(
        state: SignupScreenState,
        onEvent: (SignupScreenEvent) -> Unit = {}
    ){
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(Res.string.welcome),
                    style = MaterialTheme.typography.headlineLarge
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
                        onEvent(SignupScreenEvent.OnEmailChanged(it))
                    },
                    modifier = Modifier
                        .padding(top = 32.dp)
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
                        onEvent(SignupScreenEvent.OnPasswordChanged(it))
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
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
                    text = stringResource(Res.string.sign_up),
                    enabled = state.isEnabledButton,
                    onClick = {
                        onEvent(SignupScreenEvent.OnSignupClicked(state.email, state.password))
                    },
                )
            }
        }
    }
}