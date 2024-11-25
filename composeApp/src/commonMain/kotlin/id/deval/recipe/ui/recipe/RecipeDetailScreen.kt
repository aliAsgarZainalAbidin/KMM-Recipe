package id.deval.recipe.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.white
import id.deval.recipe.ui.recipe.effect.RecipeDetailEffect
import id.deval.recipe.ui.recipe.event.RecipeDetailEvent
import id.deval.recipe.ui.recipe.state.RecipeDetailState
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.description
import kmm_recipe.composeapp.generated.resources.done
import kmm_recipe.composeapp.generated.resources.heart
import kmm_recipe.composeapp.generated.resources.heart_2
import kmm_recipe.composeapp.generated.resources.likes
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class RecipeDetailScreen : Screen {

    @Composable
    override fun Content() {
        val recipeViewModel by appRecipeModule.instance<RecipeViewModel>()
        val recipeDetailState by recipeViewModel.recipeDetailState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            recipeViewModel.recipeDetailEffect.collectLatest { effect ->
                when (effect) {
                    is RecipeDetailEffect.OnNavigateBack -> {
                        navigator.pop()
                    }

                    is RecipeDetailEffect.OnRecipeOwnerClicked -> {

                    }
                }
            }
        }

        RecipeDetailContent(
            state = recipeDetailState,
            onEvent = recipeViewModel::onEvent
        )
    }

    @Composable
    fun RecipeDetailContent(
        state: RecipeDetailState,
        onEvent: (RecipeDetailEvent) -> Unit
    ) {
        val scrollState = rememberScrollState()

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                RecipeButton.BackButton(
                    onClick = {
                        onEvent(RecipeDetailEvent.OnNavigateBackClicked)
                    },
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp)
                )
                Image(
                    painter = painterResource(Res.drawable.Onboarding),
                    contentDescription = "onboarding",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(state.imageSize.dp)
                        .graphicsLayer {
                            translationY = scrollState.value * 0.5f
                        },
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(
                        modifier = Modifier.height(state.imageSize.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                            .background(MaterialTheme.colorScheme.onSurface)
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        RecipeCommonUI.RecipeSpacer(
                            modifier = Modifier.height(5.dp).width(40.dp)
                        )
                        Text(
                            text = state.recipe?.name ?: "",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "${state.recipe?.category} • ${state.recipe?.times}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.Onboarding),
                                    modifier = Modifier.size(32.dp).clip(RoundedCornerShape(100)),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Foto Profil"
                                )
                                Text(
                                    text = state.recipe?.recipeOwner ?: "",
                                    modifier = Modifier.padding(start = 8.dp),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                IconButton(
                                    onClick = {},
                                    enabled = false,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(100))
                                        .background(MaterialTheme.colorScheme.primary)
                                        .size(32.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.heart_2),
                                        modifier = Modifier.size(18.dp),
                                        tint = white,
                                        contentDescription = null,
                                    )
                                }
                                Text(
                                    text = buildAnnotatedString {
                                        append("${state.recipe?.likes} ")
                                        append(stringResource(Res.string.likes))
                                    },
                                    modifier = Modifier.padding(start = 8.dp),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                        RecipeCommonUI.RecipeSpacer(
                            modifier = Modifier.fillMaxWidth().height(2.dp)
                                .padding(vertical = 16.dp)
                        )
                        SectionDescription(state)
                        RecipeCommonUI.RecipeSpacer(
                            modifier = Modifier.fillMaxWidth().height(2.dp)
                                .padding(vertical = 16.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun SectionDescription(
        state: RecipeDetailState
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp)
        ){
            Text(
                text = stringResource(Res.string.description),
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = state.recipe?.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}