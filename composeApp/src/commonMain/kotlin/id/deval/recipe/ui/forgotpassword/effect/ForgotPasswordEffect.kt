package id.deval.recipe.ui.forgotpassword.effect

sealed interface ForgotPasswordEffect {
    data object NavigateToOtp : ForgotPasswordEffect
}