package id.deval.recipe.ui.main.event

import id.deval.recipe.ui.navigation.MainNavigation

sealed interface MainScreenEvent {
    data class OnMenuSelected(val menu : MainNavigation) : MainScreenEvent
    data object OnUploadSelected : MainScreenEvent
    data object OnScanSelected : MainScreenEvent
    data object OnNavigateBackClicked : MainScreenEvent
}