package id.deval.recipe.ui.home.state

import id.deval.recipe.ui.home.FilterCategory

data class HomeScreenState(
    val selectedCategory: FilterCategory = FilterCategory.default,
    val filterCategories: List<FilterCategory> = FilterCategory.values,
    val recipes: List<String> = emptyList(),
    val searchQuery: String = ""
)
