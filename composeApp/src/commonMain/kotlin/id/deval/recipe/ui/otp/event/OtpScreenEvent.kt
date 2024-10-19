package id.deval.recipe.ui.otp.event

sealed interface OtpScreenEvent {
    data class OnOtpChanged(val otp: String) : OtpScreenEvent
    data class OnVerifyClicked(val otp : String) : OtpScreenEvent
    data object OnResendClicked : OtpScreenEvent
    data object OtpTimeOut : OtpScreenEvent
}