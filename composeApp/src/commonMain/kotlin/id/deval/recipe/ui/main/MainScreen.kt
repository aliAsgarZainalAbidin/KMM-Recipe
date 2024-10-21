package id.deval.recipe.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.ui.navigation.Navigation

data class MainScreen(
    val navigate : (Navigation) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        MainScreenContent()
    }

    @Composable
    fun MainScreenContent(

    ){
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth()
                ){

                }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "Main Screen",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

}