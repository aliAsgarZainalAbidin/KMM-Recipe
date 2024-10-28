package id.deval.recipe.ui.main.state

import id.deval.recipe.ui.navigation.MainNavigation

data class MainScreenState(
    val selectedMenu: MainNavigation = MainNavigation.Home,
    val isScanBottomDialogOpen: Boolean = false
)
