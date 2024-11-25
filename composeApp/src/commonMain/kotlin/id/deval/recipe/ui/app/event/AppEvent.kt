package id.deval.recipe.ui.app.event

import id.deval.recipe.ui.navigation.Navigation

sealed interface AppEvent {
    data class NavigateToScreen(val screen: Navigation) : AppEvent
}