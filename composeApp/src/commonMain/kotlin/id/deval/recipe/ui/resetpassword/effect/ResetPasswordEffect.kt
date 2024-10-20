package id.deval.recipe.ui.resetpassword.effect

sealed interface ResetPasswordEffect {
    data class ShowToast(val message : String) : ResetPasswordEffect
    data object NavigateToMain : ResetPasswordEffect
}