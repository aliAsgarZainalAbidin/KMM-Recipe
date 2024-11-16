package id.deval.recipe.ui.notif.state

import id.deval.recipe.domain.model.NotificationModel

data class NotificationState(
    val notifications : List<NotificationModel> = emptyList()
)