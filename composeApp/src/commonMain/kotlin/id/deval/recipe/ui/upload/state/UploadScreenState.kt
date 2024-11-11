package id.deval.recipe.ui.upload.state

import id.deval.recipe.domain.model.RecipeStep

data class UploadScreenState(
    val coverPhoto : String = "",
    val foodName : String = "",
    val description : String = "",
    val duration : String = "",
    val ingredients : List<String> = emptyList(),
    val steps : List<RecipeStep> = emptyList(),
    val showDialog : Boolean = false
)
