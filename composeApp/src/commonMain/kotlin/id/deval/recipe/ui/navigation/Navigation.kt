package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument
import cafe.adriel.voyager.core.screen.Screen

interface Navigation {
    val route: String
    val navArguments: List<NamedNavArgument>
    val screen: Screen
}