package id.deval.recipe.ui.welcome

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.welcome.effect.WelcomeScreenEffect
import id.deval.recipe.ui.welcome.event.WelcomeScreenEvent
import id.deval.recipe.ui.welcome.state.WelcomeScreenState
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel : ViewModel() {
    private var _welcomeScreenState : MutableStateFlow<WelcomeScreenState> = MutableStateFlow(
        WelcomeScreenState()
    )
    val welcomeScreenState : StateFlow<WelcomeScreenState> = _welcomeScreenState

    private var _welcomeScreenEffect : MutableSharedFlow<WelcomeScreenEffect> = MutableSharedFlow()
    val welcomeScreenEffect : SharedFlow<WelcomeScreenEffect> = _welcomeScreenEffect

    fun onEvent(event : WelcomeScreenEvent){
        when(event){
            is WelcomeScreenEvent.OnGetStartedClicked -> {
                onGetStartedClicked(event.isLogin)
            }
        }
    }

    private fun onGetStartedClicked(isLogin : Boolean) {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                if(isLogin){
                    _welcomeScreenEffect.emit(WelcomeScreenEffect.NavigateToMain)
                } else {
                    _welcomeScreenEffect.emit(WelcomeScreenEffect.NavigateToLogin)
                }
            },
            onError = {

            }
        )
    }

    fun onEffect(effect: WelcomeScreenEffect, navigator: Navigator? = null) {
        when (effect) {
            is WelcomeScreenEffect.NavigateToMain -> {
                navigator?.replaceAll(AppNavigation.Main.screen)
            }

            is WelcomeScreenEffect.NavigateToLogin -> {
                navigator?.replaceAll(AppNavigation.Login.screen)
            }
        }
    }
}