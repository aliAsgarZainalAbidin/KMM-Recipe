package id.deval.recipe.ui.login.effect

sealed interface LoginScreenEffect {
    data object NavigateToMain : LoginScreenEffect
    data object NavigateToSignUp : LoginScreenEffect
    data object NavigateToGoogleLogin : LoginScreenEffect
    data object NavigateToForgotPassword : LoginScreenEffect
    data class ShowSnackbar(val message: String) : LoginScreenEffect
}