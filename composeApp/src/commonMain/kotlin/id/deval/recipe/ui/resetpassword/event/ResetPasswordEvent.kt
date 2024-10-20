package id.deval.recipe.ui.resetpassword.event

sealed interface ResetPasswordEvent {
    data class OnPasswordChanged(val password : String) : ResetPasswordEvent
    data object OnDoneClicked : ResetPasswordEvent
}