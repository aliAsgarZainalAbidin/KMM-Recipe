package id.deval.recipe.ui.forgotpassword

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import id.deval.recipe.ui.forgotpassword.effect.ForgotPasswordEffect
import id.deval.recipe.ui.forgotpassword.event.ForgotPasswordEvent
import id.deval.recipe.ui.forgotpassword.state.ForgotPasswordState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.util.isEmailValid
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel : ViewModel() {
    private var _forgotPasswordState: MutableStateFlow<ForgotPasswordState> = MutableStateFlow(
        ForgotPasswordState()
    )
    val forgotPasswordState: StateFlow<ForgotPasswordState> = _forgotPasswordState

    private var _forgotPasswordEffect: MutableSharedFlow<ForgotPasswordEffect> = MutableSharedFlow()
    val forgotPasswordEffect: SharedFlow<ForgotPasswordEffect> = _forgotPasswordEffect

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
            }

            is ForgotPasswordEvent.SignInClicked -> {
                onSignInClicked()
            }
        }
    }

    fun onEffect(effect: ForgotPasswordEffect, navigator: Navigator? = null) {
        when (effect) {
            is ForgotPasswordEffect.NavigateToOtp -> {
                navigator?.push(AppNavigation.Otp.screen)
            }
        }
    }

    private fun onEmailChanged(email: String) {
        _forgotPasswordState.update {
            it.copy(email = email, isEnabledSignIn = email.isEmailValid())
        }
    }

    private fun onSignInClicked() {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                /*
                * TODO()
                *   request api otp
                * */
                _forgotPasswordEffect.emit(
                    ForgotPasswordEffect.NavigateToOtp
                )
            },
            onError = {
                Logger.e(
                    tag = TAG,
                    messageString = it.message.toString()
                )
            }
        )
    }

    companion object {
        const val TAG = "ForgotPasswordViewModel"
    }
}