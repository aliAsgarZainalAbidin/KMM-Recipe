package id.deval.recipe.ui.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.otp.effect.OtpScreenEffect
import id.deval.recipe.ui.otp.event.OtpScreenEvent
import id.deval.recipe.ui.otp.state.OtpScreenState
import id.deval.recipe.util.safeNavigate
import kotlinx.coroutines.flow.collectLatest
import org.kodein.di.instance

@Composable
fun OtpScreen(
    navController: NavController
){
    val otpViewModel by appRecipeModule.instance<OtpViewModel>()
    val otpScreenState by otpViewModel.otpScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        otpViewModel.otpScreenEffect.collectLatest { effect ->
            when(effect){
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
    state : OtpScreenState,
    onEvent : (OtpScreenEvent) -> Unit
){

}