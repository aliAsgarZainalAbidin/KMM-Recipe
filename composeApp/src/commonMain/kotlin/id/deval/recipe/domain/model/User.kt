package id.deval.recipe.domain.model

data class User(
    val id : Int,
    val username : String,
    val totalRecipes : Int,
    val following : Int,
    val followers : Int,
    val liked : ArrayList<Recipe>,
    val recipes : ArrayList<Recipe>
)
