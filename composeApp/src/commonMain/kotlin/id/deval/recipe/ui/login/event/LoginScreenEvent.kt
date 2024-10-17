package id.deval.recipe.ui.login.event

sealed interface LoginScreenEvent {
    data class OnEmailChanged(val email: String) : LoginScreenEvent
    data class OnPasswordChanged(val password: String) : LoginScreenEvent
    data object OnShowPasswordClicked : LoginScreenEvent
    data class OnLoginClicked(val email : String, val password: String) : LoginScreenEvent
    data object OnGoogleLoginClicked : LoginScreenEvent
    data object OnSignUpClicked : LoginScreenEvent
    data object OnForgotPasswordClicked : LoginScreenEvent
}