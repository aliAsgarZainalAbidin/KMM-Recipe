package id.deval.recipe.ui.otp

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import id.deval.recipe.ui.otp.effect.OtpScreenEffect
import id.deval.recipe.ui.otp.event.OtpScreenEvent
import id.deval.recipe.ui.otp.state.OtpScreenState
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class OtpViewModel : ViewModel() {
    private var _otpScreenState : MutableStateFlow<OtpScreenState> = MutableStateFlow(OtpScreenState())
    val otpScreenState = _otpScreenState

    private var _otpScreenEffect : MutableSharedFlow<OtpScreenEffect> = MutableSharedFlow()
    val otpScreenEffect = _otpScreenEffect

    fun onEvent(event : OtpScreenEvent){
        when(event){
            is OtpScreenEvent.OnOtpChanged -> {
                onOtpChanged(event.otp)
            }

            is OtpScreenEvent.OnResendClicked -> {
                onResendClicked()
            }

            is OtpScreenEvent.OnVerifyClicked -> {
                onVerifyClicked(event.otp)
            }

            is OtpScreenEvent.OtpTimeOut -> {
                enabledResendOtp()
            }
        }
    }

    private fun onOtpChanged(otp : String){
        _otpScreenState.update {
            it.copy(otp = otp, isEnabledVerifyButton = otp.length >= 4)
        }
    }

    private fun onResendClicked(){
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                /*
                * TODO()
                *  resend otp API
                *  if(success) disabled resend button
                * */
                _otpScreenEffect.emit(OtpScreenEffect.OnResendOtp)
            },
            onError = {
                Logger.d(
                    TAG,
                    it
                )
            }
        )
    }

    private fun onVerifyClicked(otp : String){
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                /*
                * TODO()
                *  verify otp API
                *  if(success) navigate to main screen
                * */
                _otpScreenEffect.emit(OtpScreenEffect.NavigateToMain)
            },
            onError = {
                Logger.d(
                    TAG,
                    it
                )
            }
        )
    }

    private fun enabledResendOtp(){
        _otpScreenState.update {
            it.copy(isEnabledResendButton = true)
        }
    }

    companion object {
        const val TAG = "OtpScreenViewModel"
    }
}