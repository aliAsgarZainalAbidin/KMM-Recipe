package id.deval.recipe.ui.notif

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User
import id.deval.recipe.ui.notif.effect.NotificationEffect
import id.deval.recipe.ui.notif.event.NotificationEvent
import id.deval.recipe.ui.notif.state.NotificationState
import id.deval.recipe.util.DataDummy
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class NotificationViewModel : ViewModel() {
    private var _notificationState: MutableStateFlow<NotificationState> = MutableStateFlow(
        NotificationState(
            notifications = DataDummy.notifications
        )
    )
    val notificationState : StateFlow<NotificationState> = _notificationState

    private var _notificationEffect : MutableSharedFlow<NotificationEffect> = MutableSharedFlow()
    val notificationEffect : SharedFlow<NotificationEffect> = _notificationEffect

    fun onEvent(event : NotificationEvent){
        when (event){
            is NotificationEvent.OnImageClicked -> {
                onImageClicked(event.recipe)
            }
            is NotificationEvent.OnFollowClicked -> {
                onFollowClicked(event.user)
            }
            is NotificationEvent.OnUsernameClicked -> {}
        }
    }

    private fun onImageClicked(recipe : Recipe){

    }

    private fun onUsernameClicked(user : User){

    }

    private fun onFollowClicked(user : User){

    }
}