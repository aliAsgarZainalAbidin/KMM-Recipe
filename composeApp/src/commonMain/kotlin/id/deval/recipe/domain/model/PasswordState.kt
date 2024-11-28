package id.deval.recipe.domain.model

data class PasswordState(
    val isPassAtleastSix : Boolean = false,
    val isPassContaintNumber : Boolean = false,
    val isEnabledButton : Boolean = false,
)
