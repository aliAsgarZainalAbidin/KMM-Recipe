package id.deval.recipe.ui.notif

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.domain.model.NotificationModel
import id.deval.recipe.domain.model.TypeNotification
import id.deval.recipe.domain.model.toTodayNotification
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.ui.notif.event.NotificationEvent
import id.deval.recipe.ui.notif.state.NotificationState
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.kodein.di.instance

class NotificationScreen : Screen {

    @Composable
    override fun Content() {
        val notificationViewModel by appRecipeModule.instance<NotificationViewModel>()
        val notificationState by notificationViewModel.notificationState.collectAsStateWithLifecycle()

        NotificationScreenContent(
            notificationState,
            notificationViewModel::onEvent
        )
    }

    @Composable
    fun NotificationScreenContent(
        state: NotificationState,
        onEvent: (NotificationEvent) -> Unit
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                val todayNotification = state.notifications.toTodayNotification()
                if(todayNotification.isNotEmpty()){
                    item {
                        HeadNotificationSection("Today")
                    }
                    items(todayNotification){ notification ->
                        when(notification.type){
                            TypeNotification.FOLLOW -> {
                                FollowNotificationItem(notification as NotificationModel.Follow, onEvent)
                            }
                            TypeNotification.LIKED -> {
                                LikedNotificationItem(notification as NotificationModel.Liked, onEvent)
                            }
                        }
                    }
                }

                val oldNotification = state.notifications.toTodayNotification(false)
                if(oldNotification.isNotEmpty()){
                    item {
                        HeadNotificationSection("Lebih lama")
                    }

                    items(oldNotification){ notification ->
                        when(notification.type){
                            TypeNotification.FOLLOW -> {
                                FollowNotificationItem(notification as NotificationModel.Follow, onEvent)
                            }
                            TypeNotification.LIKED -> {
                                LikedNotificationItem(notification as NotificationModel.Liked, onEvent)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun HeadNotificationSection(
        title: String,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top= 24.dp, bottom = 12.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

    @Composable
    fun FollowNotificationItem(
        notification: NotificationModel.Follow,
        onEvent: (NotificationEvent) -> Unit
    ) {
        val user = notification.fromUser.first()
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.Onboarding),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
                    .size(48.dp)
                    .clip(RoundedCornerShape(100))
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = mainTextColor,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable {
                        onEvent(NotificationEvent.OnUsernameClicked(user))
                    }.fillMaxWidth()
                )
                Text(
                    text = "${notification.message} ・ ${notification.time}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            RecipeButton.FollowButton(
                text = if(notification.isFollowed) "Followed" else "Follow",
                onClick = {},
                isFollow = notification.isFollowed,
                modifier = Modifier.height(39.dp)
            )
        }
    }

    @Composable
    fun LikedNotificationItem(
        notification: NotificationModel.Liked,
        onEvent: (NotificationEvent) -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (notification.fromUser.size) {
                1 -> {
                    Image(
                        painter = painterResource(Res.drawable.Onboarding),
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
                            .size(48.dp)
                            .clip(RoundedCornerShape(100))
                    )
                }

                else -> {
                    Box(
                        modifier = Modifier
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.Onboarding),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp),
                            alignment = Alignment.TopEnd
                        )
                        Image(
                            painter = painterResource(Res.drawable.Onboarding),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp),
                            alignment = Alignment.BottomStart
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                repeat(notification.fromUser.size) { index ->
                    val user = notification.fromUser[index]
                    Text(
                        text = "${user.username} ",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = mainTextColor,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable {
                            onEvent(NotificationEvent.OnUsernameClicked(user))
                        }.fillMaxWidth()
                    )
                }

                Text(
                    text = "${notification.message} ・ ${notification.time}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(Res.drawable.Onboarding),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp))
            )
        }
    }
}