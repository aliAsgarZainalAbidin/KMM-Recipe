package id.deval.recipe.ui.forgotpassword.event

sealed interface ForgotPasswordEvent {
    data class OnEmailChanged(val email: String) : ForgotPasswordEvent
    data object SignInClicked : ForgotPasswordEvent
}