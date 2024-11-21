package id.deval.recipe.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.profile.event.ProfileScreenEvent
import id.deval.recipe.ui.profile.state.ProfileScreenState
import id.deval.recipe.ui.profile.state.ProfileTabType
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.back
import kmm_recipe.composeapp.generated.resources.follow
import kmm_recipe.composeapp.generated.resources.food_name
import kmm_recipe.composeapp.generated.resources.share
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val profileViewModel by appRecipeModule.instance<ProfileViewModel>()
        val stateProfileScreen by profileViewModel.profileScreenState.collectAsStateWithLifecycle()

        ProfileScreenContent(
            stateProfileScreen,
            profileViewModel::onEvent
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProfileScreenContent(
        state: ProfileScreenState,
        onEvent: (ProfileScreenEvent) -> Unit
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.back),
                                contentDescription = "back",
                                tint = mainTextColor
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.share),
                                contentDescription = "share",
                                tint = mainTextColor
                            )
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HeadProfileContent(state)
                RecipeCommonUI.RecipeSpacer()
                BodyProfileContent(state, onEvent)
            }
        }
    }

    @Composable
    fun HeadProfileContent(
        state: ProfileScreenState
    ) {
        val isOwnProfile = false

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.Onboarding),
                contentDescription = "photo profile",
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .size(100.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = state.user.username,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = mainTextColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.user.recipes.size.toString(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = mainTextColor
                        )
                    )
                    Text(
                        text = "Recipes",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = secondaryTextColor
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.user.following.toString(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = mainTextColor
                        )
                    )
                    Text(
                        text = "Following",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = secondaryTextColor
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.user.followers.toString(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = mainTextColor
                        )
                    )
                    Text(
                        text = "Followers",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = secondaryTextColor
                        )
                    )
                }
            }

            if (!isOwnProfile) {
                RecipeButton.FollowButton(
                    onClick = {},
                    isFollow = false,
                    text = stringResource(Res.string.follow),
                    modifier = Modifier.padding(bottom = 24.dp)
                        .widthIn(max = 263.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BodyProfileContent(
        state: ProfileScreenState,
        onEvent: (ProfileScreenEvent) -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 24.dp)
        ) {
            TabRow(
                selectedTabIndex = state.selectedTabType.ordinal,
                indicator = { tabPosition ->
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.fillMaxWidth()
                            .tabIndicatorOffset(tabPosition[state.selectedTabType.ordinal]),
                        width = tabPosition[state.selectedTabType.ordinal].width,
                        height = 4.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                ProfileTabType.entries.forEach { profileTabType ->
                    Tab(
                        selected = state.selectedTabType == profileTabType,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(ProfileScreenEvent.OnTabMenuClicked(profileTabType))
                        },
                        text = {
                            Text(
                                text = profileTabType.name,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = mainTextColor,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
        }
    }
}