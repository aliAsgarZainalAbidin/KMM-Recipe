package id.deval.recipe.ui.app.state

import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.navigation.Navigation

data class AppState(
    val currentScreen: Navigation = AppNavigation.Otp,
)