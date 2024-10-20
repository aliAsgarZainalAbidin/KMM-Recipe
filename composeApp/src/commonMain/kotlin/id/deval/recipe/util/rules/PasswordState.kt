package id.deval.recipe.util.rules

data class PasswordState(
    val isPassAtleastSix : Boolean = false,
    val isPassContaintNumber : Boolean = false,
    val isEnabledButton : Boolean = false,
)
