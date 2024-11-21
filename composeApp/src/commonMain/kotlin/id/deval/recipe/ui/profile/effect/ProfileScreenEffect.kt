package id.deval.recipe.ui.profile.effect

import id.deval.recipe.domain.model.User

sealed interface ProfileScreenEffect {
    data class ShareProfile(val user : User) : ProfileScreenEffect
    data object NavigateBack : ProfileScreenEffect
}