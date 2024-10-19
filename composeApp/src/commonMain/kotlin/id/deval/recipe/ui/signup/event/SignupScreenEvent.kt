package id.deval.recipe.ui.signup.event

sealed interface SignupScreenEvent {
    data class OnEmailChanged(val email : String) : SignupScreenEvent
    data class OnPasswordChanged(val password : String) : SignupScreenEvent
    data class OnSignupClicked(val email : String, val password : String) : SignupScreenEvent
}