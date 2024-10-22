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
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.theme.secondaryColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.navigation.MainNavigation
import id.deval.recipe.ui.navigation.Navigation
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.scan
import org.jetbrains.compose.resources.painterResource

class MainScreen : Screen {

    @Composable
    override fun Content() {
        MainScreenContent()
    }

    @Composable
    fun MainScreenContent() {
        val bottomAppBarState = remember { mutableStateOf<MainNavigation>(MainNavigation.Home) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ){
                    BottomAppBar(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            MenuBottomNavigation(
                                menu = MainNavigation.Home,
                                currentState = bottomAppBarState.value,
                                contentDescription = "home icon",
                                modifier = Modifier.weight(1f),
                                onClick = { bottomAppBarState.value = it}
                            )
                            MenuBottomNavigation(
                                menu = MainNavigation.Upload,
                                currentState = bottomAppBarState.value,
                                contentDescription = "upload icon",
                                modifier = Modifier.weight(1f),
                                onClick = { bottomAppBarState.value = it}
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable(
                                        enabled = false, onClick = {}
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ){
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = MainNavigation.Scan.title,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            MenuBottomNavigation(
                                menu = MainNavigation.Notification,
                                currentState = bottomAppBarState.value,
                                contentDescription = "notification icon",
                                modifier = Modifier.weight(1f),
                                onClick = { bottomAppBarState.value = it}
                            )
                            MenuBottomNavigation(
                                menu = MainNavigation.Profile,
                                currentState = bottomAppBarState.value,
                                contentDescription = "profile icon",
                                modifier = Modifier.weight(1f),
                                onClick = { bottomAppBarState.value = it}
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        RecipeButton.DefaultCircleFilledButton(
                            icon = painterResource(Res.drawable.scan),
                            onClick = {},
                            modifier = Modifier.padding(bottom = 50.dp)
                        )
                    }
                }
            }
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Main Screen",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

    @Composable
    fun MenuBottomNavigation(
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