package id.deval.recipe.ui.login.state

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val showPassword : Boolean = false
)
