package id.deval.recipe.ui.detail

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.white
import id.deval.recipe.ui.detail.effect.RecipeDetailEffect
import id.deval.recipe.ui.detail.event.RecipeDetailEvent
import id.deval.recipe.ui.detail.state.RecipeDetailState
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.description
import kmm_recipe.composeapp.generated.resources.heart_2
import kmm_recipe.composeapp.generated.resources.ingredients
import kmm_recipe.composeapp.generated.resources.likes
import kmm_recipe.composeapp.generated.resources.steps
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
                Image(
                    painter = painterResource(Res.drawable.Onboarding),
                    contentDescription = "onboarding",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(state.imageSize.plus(32).dp)
                        .graphicsLayer {
                            translationY = scrollState.value * -0.5f
                            Logger.d(
                                tag = "Scroll State",
                                messageString = "ScrollState : ${scrollState.value}, translation $translationY"
                            )
                        },
                    contentScale = ContentScale.Crop
                )

                RecipeButton.BackButton(
                    onClick = {
                        onEvent(RecipeDetailEvent.OnNavigateBackClicked)
                    },
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp)
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
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
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
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            textAlign = TextAlign.Start
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier,
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
                                modifier = Modifier,
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
                        RecipeCommonUI.RecipeSpacer(modifier = Modifier.fillMaxWidth().height(2.dp))
                        SectionDescription(state)
                        RecipeCommonUI.RecipeSpacer(modifier = Modifier.fillMaxWidth().height(2.dp))
                        SectionIngredients(state)
                        RecipeCommonUI.RecipeSpacer(modifier = Modifier.fillMaxWidth().height(2.dp))
                        SectionSteps(state)
                    }
                }
            }
        }
    }

    @Composable
    fun SectionDescription(
        state: RecipeDetailState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.description),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = state.recipe?.description ?: "null",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = mainTextColor
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }

    @Composable
    fun SectionIngredients(
        state: RecipeDetailState
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.ingredients),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            repeat(state.recipe?.ingredients?.size ?: 0) { index ->
                RecipeCommonUI.IngredientItem(state.recipe?.ingredients?.get(index) ?: "")
            }
        }
    }

    @Composable
    fun SectionSteps(
        state: RecipeDetailState
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.steps),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            repeat(state.recipe?.steps?.size ?: 0) { index ->
                state.recipe?.steps?.get(index).let { step ->
                    if (step != null) {
                        RecipeCommonUI.StepItem(index.plus(1).toString(), step)
                    }
                }
            }
        }
    }


}