package id.deval.recipe.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.category
import org.jetbrains.compose.resources.stringResource

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }

    @Composable
    fun HomeScreenContent(){
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {

            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ){
                Text(
                    text = stringResource(Res.string.category),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

            }
        }
    }

    @Composable
    fun FilterCategory(){

    }
}