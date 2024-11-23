package id.deval.recipe.ui.home.event

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.ui.home.FilterCategory
import id.deval.recipe.util.RecipeSliderValue

sealed interface HomeScreenEvent {
    data class OnCategorySelected(val category: FilterCategory) : HomeScreenEvent
    data class OnSearchQueryChanged(val query: String) : HomeScreenEvent
    data class OnRecipeClicked(val recipe: Recipe) : HomeScreenEvent
    data class OnLikeClicked(val recipe: Recipe) : HomeScreenEvent
    data object OnCloseSearchField : HomeScreenEvent
    data class OnFilterClicked(val state : Boolean) : HomeScreenEvent
    data class OnDurationChanged(val duration : String) : HomeScreenEvent
    data class OnFilterDoneClicked(val duration : RecipeSliderValue, val category: FilterCategory, val modalState : Boolean) : HomeScreenEvent
}