package id.deval.recipe.ui.recipe.effect

import id.deval.recipe.domain.model.User

sealed interface RecipeDetailEffect {
    data object OnNavigateBack : RecipeDetailEffect
    data class OnRecipeOwnerClicked(val user: User) : RecipeDetailEffect
}