package id.deval.recipe.ui.home.event

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.ui.home.FilterCategory

sealed interface HomeScreenEvent {
    data class OnCategorySelected(val category: FilterCategory) : HomeScreenEvent
    data class OnSearchQueryChanged(val query: String) : HomeScreenEvent
    data class OnRecipeClicked(val recipe: Recipe) : HomeScreenEvent
    data class OnLikeClicked(val recipe: Recipe) : HomeScreenEvent
}