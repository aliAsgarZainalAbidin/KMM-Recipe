package id.deval.recipe.ui.main

import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.main.effect.MainScreenEffect
import id.deval.recipe.ui.main.event.MainScreenEvent
import id.deval.recipe.ui.main.state.MainScreenState
import id.deval.recipe.ui.navigation.MainNavigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private var  _mainScreenState : MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState()
    )
    val mainScreenState : StateFlow<MainScreenState> = _mainScreenState

    private var _mainScreenEffect : MutableSharedFlow<MainScreenEffect> = MutableSharedFlow()
    val mainScreenEffect : SharedFlow<MainScreenEffect> = _mainScreenEffect

    fun onEvent(event : MainScreenEvent){
        when(event){
            is MainScreenEvent.OnMenuSelected -> {
                onMenuSelected(event.menu)
            }
            is MainScreenEvent.OnScanSelected -> {
                onScanSelected(event.state)
            }
            is MainScreenEvent.OnNavigateBackClicked -> {
                onNavigateBack()
            }
        }
    }

    private fun onMenuSelected(menu : MainNavigation){
        _mainScreenState.update {
            it.copy(selectedMenu = menu)
        }
    }

    private fun onScanSelected(state : Boolean){
        _mainScreenState.update {
            it.copy(isScanBottomDialogOpen = state)
        }
    }

    private fun onNavigateBack(){

    }
}