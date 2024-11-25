package id.deval.recipe.ui.app

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.app.event.AppEvent
import id.deval.recipe.ui.app.state.AppState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.navigation.Navigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private var _appScreenState : MutableStateFlow<AppState> = MutableStateFlow(AppState())
    val appScreenState : StateFlow<AppState> = _appScreenState

    fun onEvent(event : AppEvent){
        when(event){
            is AppEvent.NavigateToScreen -> {
                onNavigateToScreen(event.screen)
            }
        }
    }

    private fun onNavigateToScreen(screen : Navigation){
        _appScreenState.update {
            it.copy(currentScreen = screen)
        }
    }
}