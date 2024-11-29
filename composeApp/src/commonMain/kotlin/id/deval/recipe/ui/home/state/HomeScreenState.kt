package id.deval.recipe.ui.home.state

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.FilterCategory
import id.deval.recipe.util.RecipeSliderValue

data class HomeScreenState(
    val selectedCategory: FilterCategory = FilterCategory.default,
    val selectedDurationRecipe : RecipeSliderValue = RecipeSliderValue.LESS_THAN_10,
    val filterCategories: List<FilterCategory> = FilterCategory.values,
    val recipes: List<Recipe?> = emptyList(),
    val filteredRecipes : List<Recipe?> = emptyList(),
    val selectedRecipe: Recipe? = null,
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    val showBottomModalFilter : Boolean = false
)
