package id.deval.recipe.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.shared.PlatformTarget
import id.deval.recipe.shared.getPlatform
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.home.HomeScreen
import id.deval.recipe.ui.main.effect.MainScreenEffect
import id.deval.recipe.ui.main.event.MainScreenEvent
import id.deval.recipe.ui.main.state.MainScreenState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.navigation.MainNavigation
import id.deval.recipe.ui.upload.UploadScreenFirstStep
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.scan
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.kodein.di.instance

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val mainScreenViewModel by appRecipeModule.instance<MainViewModel>()
        val mainScreenState by mainScreenViewModel.mainScreenState.collectAsStateWithLifecycle()
        val localNavigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            mainScreenViewModel.mainScreenEffect.collectLatest { effect ->
                mainScreenViewModel.onEffect(effect, localNavigator)
            }
        }

        MainScreenContent(
            mainScreenState,
            mainScreenViewModel::onEvent
        )
    }

    @Composable
    fun MainScreenContent(
        state: MainScreenState,
        onEvent: (MainScreenEvent) -> Unit = {}
    ) {
        val platform = getPlatform().name

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    BottomAppBar(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MenuBottomNavigation(state.selectedMenu, onEvent)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RecipeButton.DefaultCircleFilledButton(
                            icon = painterResource(Res.drawable.scan),
                            onClick = {
                                when(platform){
                                    PlatformTarget.ANDROID -> {
//                                        FeatureRequirePermission()
                                    }
                                    PlatformTarget.DESKTOP -> {}
                                    PlatformTarget.IOS -> {}
                                    PlatformTarget.WEB -> {}
                                }
                                onEvent(MainScreenEvent.OnScanSelected)
                            },
                            modifier = Modifier.padding(bottom = 50.dp)
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (state.selectedMenu) {
                    MainNavigation.Home -> {
                        Navigator(MainNavigation.Home.screen)
                    }

                    MainNavigation.Notification -> {
                        Navigator(MainNavigation.Notification.screen)
                    }

                    MainNavigation.Profile -> {
                        Navigator(MainNavigation.Profile.screen)
                    }

                    else -> {}
                }
            }
        }
    }

    @Composable
    fun MenuBottomNavigation(
        bottomAppBarState: MainNavigation,
        onEvent: (MainScreenEvent) -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItemBottomNavigation(
                menu = MainNavigation.Home, currentState = bottomAppBarState,
                contentDescription = "home icon", modifier = Modifier.weight(1f),
                onClick = {
                    onEvent(MainScreenEvent.OnMenuSelected(it))
                }
            )
            MenuItemBottomNavigation(
                menu = MainNavigation.Upload, currentState = bottomAppBarState,
                contentDescription = "upload icon", modifier = Modifier.weight(1f),
                onClick = {
                    onEvent(MainScreenEvent.OnUploadSelected)
                }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(enabled = false, onClick = {}),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = MainNavigation.Scan.title,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }
            MenuItemBottomNavigation(
                menu = MainNavigation.Notification, currentState = bottomAppBarState,
                contentDescription = "notification icon", modifier = Modifier.weight(1f),
                onClick = {
                    onEvent(MainScreenEvent.OnMenuSelected(it))
                }
            )
            MenuItemBottomNavigation(
                menu = MainNavigation.Profile, currentState = bottomAppBarState,
                contentDescription = "profile icon", modifier = Modifier.weight(1f),
                onClick = {
                    onEvent(MainScreenEvent.OnMenuSelected(it))
                }
            )
        }
    }

    @Composable
    fun MenuItemBottomNavigation(
        menu: MainNavigation,
        currentState: MainNavigation,
        contentDescription: String,
        modifier: Modifier,
        onClick: (MainNavigation) -> Unit = {}
    ) {
        val contentColor =
            if (currentState == menu) MaterialTheme.colorScheme.primary else secondaryTextColor
        Column(
            modifier = modifier
                .clickable {
                    onClick(menu)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(menu.icon),
                contentDescription = contentDescription,
                modifier = modifier,
                tint = contentColor
            )
            Text(
                text = menu.title,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = contentColor
                ),
                modifier = modifier
            )
        }
    }
}