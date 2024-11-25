package id.deval.recipe.ui.detail.state

import id.deval.recipe.domain.model.Recipe

data class RecipeDetailState(
    val recipe : Recipe? = null,
    val imageSize : Int = 300,
)