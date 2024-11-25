package id.deval.recipe.di

import id.deval.recipe.ui.app.AppViewModel
import id.deval.recipe.ui.forgotpassword.ForgotPasswordViewModel
import id.deval.recipe.ui.home.HomeViewModel
import id.deval.recipe.ui.login.LoginViewModel
import id.deval.recipe.ui.main.MainViewModel
import id.deval.recipe.ui.notif.NotificationViewModel
import id.deval.recipe.ui.otp.OtpViewModel
import id.deval.recipe.ui.profile.ProfileViewModel
import id.deval.recipe.ui.detail.RecipeViewModel
import id.deval.recipe.ui.resetpassword.ResetPasswordViewModel
import id.deval.recipe.ui.scan.ScanViewModel
import id.deval.recipe.ui.signup.SignupViewModel
import id.deval.recipe.ui.upload.UploadViewModel
import id.deval.recipe.ui.welcome.WelcomeViewModel
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val viewModelModule = DI.Module("viewModelModule"){
    bindSingleton { AppViewModel() }
    bindSingleton { LoginViewModel() }
    bindSingleton { WelcomeViewModel() }
    bindSingleton { SignupViewModel() }
    bindSingleton { OtpViewModel() }
    bindSingleton { ForgotPasswordViewModel() }
    bindSingleton { ResetPasswordViewModel() }
    bindSingleton { MainViewModel() }
    bindSingleton { HomeViewModel() }
    bindSingleton { UploadViewModel() }
    bindSingleton { ScanViewModel() }
    bindSingleton { NotificationViewModel() }
    bindSingleton { ProfileViewModel() }
    bindSingleton { RecipeViewModel() }
}