package id.deval.recipe.ui.signup

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import id.deval.recipe.ui.signup.effect.SignupScreenEffect
import id.deval.recipe.ui.signup.event.SignupScreenEvent
import id.deval.recipe.ui.signup.state.SignupScreenState
import id.deval.recipe.util.isEmailValid
import id.deval.recipe.util.isPhoneNumberValid
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {
    private var _signupScreenState: MutableStateFlow<SignupScreenState> = MutableStateFlow(
        SignupScreenState()
    )
    val signupScreenState: StateFlow<SignupScreenState> = _signupScreenState

    private var _signupScreenEffect: MutableSharedFlow<SignupScreenEffect> = MutableSharedFlow()
    val signupScreenEffect: SharedFlow<SignupScreenEffect> = _signupScreenEffect

    fun onEvent(event: SignupScreenEvent) {
        when (event) {
            is SignupScreenEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
                enabledButton()
            }

            is SignupScreenEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
                enabledButton()
            }

            is SignupScreenEvent.OnSignupClicked -> {
                onSignupClicked(event.email, event.password)
            }
        }
    }

    private fun onEmailChanged(email: String) {
        _signupScreenState.update {
            it.copy(email = email)
        }
    }

    private fun onPasswordChanged(password: String) {
        _signupScreenState.update {
            it.copy(password = password)
        }
        atleastSixCharacter(password)
        containsNumber(password)
    }

    private fun atleastSixCharacter(password: String) {
        _signupScreenState.update {
            it.copy(isPassAtleastSix = password.length >= 6)
        }
    }

    private fun containsNumber(password: String) {
        _signupScreenState.update {
            it.copy(isPassContaintNumber = password.any {
                it.isDigit()
            })
        }
    }

    private fun enabledButton() {
        val isEnabled = _signupScreenState.value.isPassAtleastSix &&
                _signupScreenState.value.isPassContaintNumber &&
                _signupScreenState.value.email.isNotEmpty() &&
                _signupScreenState.value.password.isNotEmpty() &&
                (_signupScreenState.value.email.isEmailValid() || _signupScreenState.value.email.isPhoneNumberValid())
        _signupScreenState.update {
            it.copy(isEnabledButton = isEnabled)
        }
    }

    private fun onSignupClicked(email: String, password: String) {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                //TODO()
                /*
                * request signup API
                * */
                _signupScreenEffect.emit(SignupScreenEffect.NavigateToOtp)
            },
            onError = {
                _signupScreenEffect.emit(SignupScreenEffect.ShowToast(it.message ?: "Error"))
                Logger.e(
                    TAG,
                    it
                )
            }
        )
    }


    companion object {
        const val TAG = "SignupViewModel"
    }
}