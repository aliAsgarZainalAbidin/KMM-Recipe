package id.deval.recipe.util.rules

import id.deval.recipe.domain.model.PasswordState

interface PasswordRules {
    private fun isPasswordAtleastSix(password : String) : Boolean {
        return password.length >= 6
    }

    private fun isPasswordContainNumber(password : String) : Boolean {
        return password.any {
            it.isDigit()
        }
    }

    fun validatePassword(password : String) : PasswordState {
        val isPasswordAtleastSix = isPasswordAtleastSix(password)
        val isPasswordContainNumber = isPasswordContainNumber(password)
        return PasswordState(
            isPassAtleastSix = isPasswordAtleastSix,
            isPassContaintNumber = isPasswordContainNumber,
            isEnabledButton = isPasswordContainNumber && isPasswordAtleastSix
        )
    }
}