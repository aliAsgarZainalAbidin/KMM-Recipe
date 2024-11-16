package id.deval.recipe.domain.model

sealed class NotificationModel {
    abstract val id : Int
    abstract val fromUser : ArrayList<User>
    abstract val message : String
    abstract val time : String
    abstract val type : TypeNotification

    data class Follow(
        override val id: Int,
        override val fromUser: ArrayList<User>,
        override val message: String,
        override val time: String,
        val isFollowed : Boolean,
        override val type: TypeNotification = TypeNotification.FOLLOW
    ) : NotificationModel()

    data class Liked(
        override val id: Int,
        override val fromUser: ArrayList<User>,
        override val message: String,
        override val time: String,
        val isLiked : Boolean = true,
        override val type: TypeNotification = TypeNotification.LIKED
    ) : NotificationModel()

}

enum class TypeNotification {
    FOLLOW, LIKED
}

