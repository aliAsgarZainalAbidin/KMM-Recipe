package id.deval.recipe.ui.notif.effect

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User

sealed interface NotificationEffect {
    data class OnUsernameClicked(val user: User) : NotificationEffect
    data class OnImageClicked(val recipe: Recipe) : NotificationEffect
    data class OnFollowClicked(val user : User) : NotificationEffect
}