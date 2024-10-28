package id.deval.recipe.ui.home.effect

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.ui.home.FilterCategory

sealed interface HomeScreenEffect {
    data class NavigateToDetail(val recipe : Recipe) : HomeScreenEffect
    data class OnChangedFilterCategory(val category: FilterCategory) : HomeScreenEffect
    data class OnChangedSearchQuery(val query: String) : HomeScreenEffect
}