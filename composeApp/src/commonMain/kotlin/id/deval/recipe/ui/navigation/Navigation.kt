package id.deval.recipe.ui.navigation

import androidx.navigation.NamedNavArgument

interface Navigation {
    val route : String
    val navArguments : List<NamedNavArgument>
}