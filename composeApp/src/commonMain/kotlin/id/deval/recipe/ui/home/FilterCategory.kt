package id.deval.recipe.ui.home

import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.all
import kmm_recipe.composeapp.generated.resources.drink
import kmm_recipe.composeapp.generated.resources.food
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

sealed class FilterCategory(
    val name : StringResource
) {
    data class All(val isSelected : Boolean): FilterCategory(Res.string.all)
    data class Food(val isSelected: Boolean): FilterCategory(Res.string.food)
    data class Drink(val isSelected: Boolean): FilterCategory(Res.string.drink)

    companion object{
        val default = All(true)
        val values = listOf(All(true), Food(false), Drink(false))
    }
}