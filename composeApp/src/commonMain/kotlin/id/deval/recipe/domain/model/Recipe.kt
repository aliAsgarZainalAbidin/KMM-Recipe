package id.deval.recipe.domain.model

data class Recipe(
    val id : String,
    val name : String,
    val category : String,
    val times : String,
    val recipeOwner : String,
    val idRecipeOwner : String,
    val likes : Int,
    val description : String,
    val ingredients : List<String>,
    val steps : List<RecipeStep>,
    val isLiked : Boolean
)
