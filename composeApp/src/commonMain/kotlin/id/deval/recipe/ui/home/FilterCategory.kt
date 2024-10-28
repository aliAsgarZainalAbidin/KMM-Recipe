package id.deval.recipe.ui.home

sealed class FilterCategory {
    data class All(val isSelected : Boolean): FilterCategory()
    data class Food(val isSelected: Boolean): FilterCategory()
    data class Drink(val isSelected: Boolean): FilterCategory()

    companion object{
        val default = All(true)
        val values = listOf(All(true), Food(false), Drink(false))
    }
}