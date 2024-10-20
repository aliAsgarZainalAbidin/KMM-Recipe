package id.deval.recipe.di

import id.deval.recipe.ui.forgotpassword.ForgotPasswordViewModel
import id.deval.recipe.ui.login.LoginViewModel
import id.deval.recipe.ui.otp.OtpViewModel
import id.deval.recipe.ui.resetpassword.ResetPasswordViewModel
import id.deval.recipe.ui.signup.SignupViewModel
import id.deval.recipe.ui.welcome.WelcomeViewModel
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val viewModelModule = DI.Module("viewModelModule"){
    bindSingleton { LoginViewModel() }
    bindSingleton { WelcomeViewModel() }
    bindSingleton { SignupViewModel() }
    bindSingleton { OtpViewModel() }
    bindSingleton { ForgotPasswordViewModel() }
    bindSingleton { ResetPasswordViewModel() }
}