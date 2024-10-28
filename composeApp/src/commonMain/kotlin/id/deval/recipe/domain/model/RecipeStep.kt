package id.deval.recipe.domain.model

data class RecipeStep(
    val id : String,
    val recipeId : String,
    val description : String,
    val image : String?,
)
