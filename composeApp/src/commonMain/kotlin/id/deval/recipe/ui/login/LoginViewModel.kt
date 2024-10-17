package id.deval.recipe.ui.login

import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.login.effect.LoginScreenEffect
import id.deval.recipe.ui.login.event.LoginScreenEvent
import id.deval.recipe.ui.login.state.LoginScreenState
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel() {

    private var _loginScreenState: MutableStateFlow<LoginScreenState> =
        MutableStateFlow(LoginScreenState())
    val loginScreenState: StateFlow<LoginScreenState> = _loginScreenState

    private var _loginScreenEffect: MutableSharedFlow<LoginScreenEffect?> = MutableSharedFlow()
    val loginScreenEffect: MutableSharedFlow<LoginScreenEffect?> = _loginScreenEffect

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
            }

            is LoginScreenEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
            }

            is LoginScreenEvent.OnShowPasswordClicked -> {
                onShowPasswordClicked()
            }

            is LoginScreenEvent.OnLoginClicked -> {
                onLoginClicked(event.email, event.password)
            }
            is LoginScreenEvent.OnGoogleLoginClicked -> {}
            is LoginScreenEvent.OnSignUpClicked -> {}
            is LoginScreenEvent.OnForgotPasswordClicked -> {}
        }
    }

    private fun onEmailChanged(email: String) {
        _loginScreenState.value = _loginScreenState.value.copy(email = email)
    }

    private fun onPasswordChanged(password: String) {
        _loginScreenState.value = _loginScreenState.value.copy(password = password)
    }

    private fun onShowPasswordClicked() {
        _loginScreenState.value =
            _loginScreenState.value.copy(showPassword = !_loginScreenState.value.showPassword)
    }

    private fun onLoginClicked(
        email: String,
        password: String
    ){
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                //TODO()
                /*
                * request login API
                * */

                if(email == "admin" && password == "admin"){
                    _loginScreenEffect.emit(LoginScreenEffect.NavigateToMain)
                } else {
                    _loginScreenEffect.emit(
                        LoginScreenEffect.ShowSnackbar("Email or password is wrong")
                    )
                }
            },
            onError = {
                _loginScreenEffect.emit(LoginScreenEffect.ShowSnackbar("Error"))
            }
        )
    }
}