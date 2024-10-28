package id.deval.recipe.ui.main.effect

import id.deval.recipe.ui.navigation.MainNavigation

sealed interface MainScreenEffect {
    data class OnMenuSelected(val menu : MainNavigation) : MainScreenEffect
    data class OnScanSelected(val state : Boolean) : MainScreenEffect
}