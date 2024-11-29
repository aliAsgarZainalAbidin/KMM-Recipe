package id.deval.recipe.ui.resetpassword

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.resetpassword.effect.ResetPasswordEffect
import id.deval.recipe.ui.resetpassword.event.ResetPasswordEvent
import id.deval.recipe.ui.resetpassword.state.ResetPasswordState
import id.deval.recipe.util.launchCatchError
import id.deval.recipe.util.rules.PasswordRules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ResetPasswordViewModel : ViewModel(), PasswordRules {
    private val _resetPasswordState: MutableStateFlow<ResetPasswordState> =
        MutableStateFlow(ResetPasswordState())
    val resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState

    private val _resetPasswordEffect: MutableSharedFlow<ResetPasswordEffect> = MutableSharedFlow()
    val resetPasswordEffect: SharedFlow<ResetPasswordEffect> = _resetPasswordEffect

    fun onEffect(effect : ResetPasswordEffect, navigator : Navigator? = null){
        when(effect){
            is ResetPasswordEffect.ShowToast -> {

            }
            is ResetPasswordEffect.NavigateToMain -> {
                navigator?.replaceAll(AppNavigation.Main.screen)
            }
        }
    }

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
            }

            is ResetPasswordEvent.OnDoneClicked -> {
                onDoneClicked()
            }
        }
    }

    private fun onPasswordChanged(password: String) {
        val authState = validatePassword(password)
        _resetPasswordState.update {
            it.copy(
                password = password,
                isPassAtleastSix = authState.isPassAtleastSix,
                isPassContaintNumber = authState.isPassContaintNumber,
                isEnabledButton = authState.isEnabledButton
            )
        }
    }

    private fun onDoneClicked(){
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                //TODO()
                /*
                * request reset password API
                * */
                _resetPasswordEffect.emit(ResetPasswordEffect.NavigateToMain)
            },
            onError = {
                _resetPasswordEffect.emit(ResetPasswordEffect.ShowToast(it.message ?: "Error"))
                Logger.e(
                    TAG,
                    it
                )
            }
        )
    }


    companion object {
        const val TAG = "ResetPasswordViewModel"
    }
}