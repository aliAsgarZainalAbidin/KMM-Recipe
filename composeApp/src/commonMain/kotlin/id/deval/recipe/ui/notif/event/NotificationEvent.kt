package id.deval.recipe.ui.notif.event

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User

sealed interface NotificationEvent {
    data class OnUsernameClicked(val user: User) : NotificationEvent
    data class OnImageClicked(val recipe: Recipe) : NotificationEvent
    data class OnFollowClicked(val user : User) : NotificationEvent
}