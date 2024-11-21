package id.deval.recipe.domain.model

import co.touchlab.kermit.Logger
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed class NotificationModel {
    abstract val id: Int
    abstract val fromUser: ArrayList<User>
    abstract val message: String
    abstract val time: String
    abstract val type: TypeNotification

    data class Follow(
        override val id: Int,
        override val fromUser: ArrayList<User>,
        override val message: String,
        override val time: String,
        val isFollowed: Boolean,
        override val type: TypeNotification = TypeNotification.FOLLOW
    ) : NotificationModel()

    data class Liked(
        override val id: Int,
        override val fromUser: ArrayList<User>,
        override val message: String,
        override val time: String,
        val isLiked: Boolean = true,
        override val type: TypeNotification = TypeNotification.LIKED
    ) : NotificationModel()

}

enum class TypeNotification {
    FOLLOW, LIKED
}

fun List<NotificationModel>.toTodayNotification(isToday: Boolean = true): List<NotificationModel> {
    val clockInstant = Clock.System.now()
    val currentDate = clockInstant.toLocalDateTime(TimeZone.currentSystemDefault()).date

    return this.filter {
        val notificationDate = LocalDateTime.parse(it.time).date
        if (isToday) currentDate == notificationDate else currentDate != notificationDate
    }
}

