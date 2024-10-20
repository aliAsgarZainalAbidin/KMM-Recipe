package id.deval.recipe.ui.resetpassword.state

data class ResetPasswordState(
    val password : String = "",
    val isPassAtleastSix : Boolean = false,
    val isPassContaintNumber : Boolean = false,
    val isEnabledButton : Boolean = false
)
