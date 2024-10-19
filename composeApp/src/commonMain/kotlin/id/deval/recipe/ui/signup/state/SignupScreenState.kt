package id.deval.recipe.ui.signup.state

data class SignupScreenState(
    val email : String = "",
    val password : String = "",
    val isPassContaintNumber : Boolean = false,
    val isPassAtleastSix : Boolean = false,
    val isEnabledButton : Boolean = false
)
