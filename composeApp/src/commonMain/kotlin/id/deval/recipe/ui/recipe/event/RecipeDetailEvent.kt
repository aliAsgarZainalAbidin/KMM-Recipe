package id.deval.recipe.ui.recipe.event

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User

sealed interface RecipeDetailEvent {
    data class OnRecipeOwnerClicked(val user: User) : RecipeDetailEvent
    data object OnNavigateBackClicked : RecipeDetailEvent
    data class OnRecipeClicked(val recipe: Recipe) : RecipeDetailEvent
}