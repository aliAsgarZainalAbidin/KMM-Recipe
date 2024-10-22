package id.deval.recipe.ui.otp.effect

sealed interface OtpScreenEffect {
    data class ShowToast(val message: String) : OtpScreenEffect
    data object OnResendOtp : OtpScreenEffect
    data object NavigateToMain : OtpScreenEffect
    data object NavigateToResetPassword : OtpScreenEffect
}