package id.deval.recipe.ui.signup.effect

sealed interface SignupScreenEffect {
    data class ShowToast(val message: String) : SignupScreenEffect
    data object NavigateToOtp : SignupScreenEffect
    data object NavigateBack : SignupScreenEffect
}