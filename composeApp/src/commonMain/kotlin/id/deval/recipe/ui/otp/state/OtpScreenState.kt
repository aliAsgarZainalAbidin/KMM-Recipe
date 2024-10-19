package id.deval.recipe.ui.otp.state

data class OtpScreenState(
    val otp: String = "",
    val isError: Boolean = false,
    val time : String = "",
    val isEnabledVerifyButton : Boolean = false,
    val isEnabledResendButton : Boolean = false,
)
