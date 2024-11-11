package id.deval.recipe.ui.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import co.touchlab.kermit.Logger
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeCommonUI.HeaderUploadStep
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.DefaultFilledButtonStyle
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_ingredient
import kmm_recipe.composeapp.generated.resources.add_step
import kmm_recipe.composeapp.generated.resources.back
import kmm_recipe.composeapp.generated.resources.back_to_home
import kmm_recipe.composeapp.generated.resources.close
import kmm_recipe.composeapp.generated.resources.hbday
import kmm_recipe.composeapp.generated.resources.ingredients
import kmm_recipe.composeapp.generated.resources.next
import kmm_recipe.composeapp.generated.resources.recipe_uploaded
import kmm_recipe.composeapp.generated.resources.second_step
import kmm_recipe.composeapp.generated.resources.steps
import kmm_recipe.composeapp.generated.resources.upload_success
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class UploadScreenSecondStep : Screen {

    @Composable
    override fun Content() {
        val uploadScreenViewModel by appRecipeModule.instance<UploadViewModel>()
        val uploadScreenState by uploadScreenViewModel.uploadScreenState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            uploadScreenViewModel.uploadScreenEffect.collectLatest { effect ->
                when (effect) {
                    is UploadScreenEffect.NavigateToHome -> {
                        navigator?.popUntil {
                            Logger.d(
                                tag = "CHECK Screen",
                                messageString = "Main Screen in stack ? ${it == AppNavigation.Main.screen}"
                            )
                            it == AppNavigation.Main.screen
                        }
                    }
                    is UploadScreenEffect.NavigateToFirstStep -> {
                        navigator?.pop()
                    }
                    is UploadScreenEffect.ShowToast -> {}
                    is UploadScreenEffect.NavigateToSecondStep -> {}
                    is UploadScreenEffect.NavigateToDetail -> {}
                }
            }
        }

        UploadSCreenSecondStepContent(
            uploadScreenState,
            uploadScreenViewModel::onEvent
        )
    }

    @Composable
    fun UploadSCreenSecondStepContent(
        state: UploadScreenState,
        onEvent: (UploadScreenEvent) -> Unit
    ) {
        val customModifier = Modifier.padding(start = 24.dp, end = 24.dp)
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 12.dp, bottom = 24.dp),
            bottomBar = {
                Row(
                    modifier = customModifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth(),
                ) {
                    RecipeButton.DefaultFilledButton(
                        onClick = {
                            onEvent(UploadScreenEvent.OnBackClicked)
                        },
                        text = stringResource(Res.string.back),
                        color = DefaultFilledButtonStyle().copy(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = mainTextColor,
                        ),
                        modifier = Modifier.weight(0.5f)
                    )
                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                    RecipeButton.DefaultFilledButton(
                        onClick = {
                            if (!state.showDialog) {
                                onEvent(UploadScreenEvent.ShowDialog(true))
                            }
                        },
                        text = stringResource(Res.string.next),
                        modifier = Modifier.weight(0.5f),
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 64.dp)
            ) {
                HeaderUploadStep(
                    onCancel = {
                        onEvent(UploadScreenEvent.OnBackClicked)
                    },
                    modifier = customModifier,
                    stringResource = Res.string.second_step
                )
                Column(
                    modifier = Modifier.verticalScroll(
                        state = rememberScrollState(),
                    )
                ) {
                    SectionIngredients(
                        state = state,
                        modifier = customModifier,
                        onEvent = onEvent
                    )
                    RecipeCommonUI.RecipeSpacer()
                    SectionSteps(
                        state = state,
                        modifier = customModifier,
                        onEvent = onEvent
                    )
                }
            }
            if (state.showDialog) {
                RecipeCommonUI.RecipeDialog(
                    modifier = Modifier,
                    onDismissRequest = {}
                ) {
                    Column(
                        modifier = Modifier.padding(
                            top = 48.dp, bottom = 48.dp, start = 42.dp, end = 42.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.hbday),
                            modifier = Modifier.size(160.dp),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(Res.string.upload_success),
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Text(
                            text = stringResource(Res.string.recipe_uploaded),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        RecipeButton.DefaultFilledButton(
                            onClick = {
                                onEvent(UploadScreenEvent.ShowDialog(false))
                            },
                            modifier = Modifier.padding(top = 24.dp),
                            text = stringResource(Res.string.back_to_home)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun SectionIngredients(
        state: UploadScreenState,
        modifier: Modifier,
        onEvent: (UploadScreenEvent) -> Unit
    ) {
        Column(modifier) {
            Text(
                text = stringResource(Res.string.ingredients),
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
            repeat(state.ingredients.size) { index ->
                RecipeTextField.DragableTextField(
                    modifier = Modifier.padding(top = 24.dp),
                    value = state.ingredients[index],
                    onValueChange = {
                        onEvent(UploadScreenEvent.OnIngredientChanged(it, index))
                    }
                )
            }
            RecipeButton.DefaultTextButton(
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
                text = stringResource(Res.string.add_ingredient),
                startIcon = painterResource(Res.drawable.close),
                onClick = {
                    onEvent(UploadScreenEvent.OnAddIngredientClicked)
                }
            )
        }
    }

    @Composable
    fun SectionSteps(
        modifier: Modifier,
        state: UploadScreenState,
        onEvent: (UploadScreenEvent) -> Unit
    ) {
        Column(
            modifier
        ) {
            Text(
                text = stringResource(Res.string.steps),
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
            repeat(
                state.steps.size,
            ) { index ->
                RecipeTextField.DragableStepInputField(
                    modifier = Modifier.padding(top = 24.dp),
                    numberSteps = index.toString(),
                    value = state.steps[index],
                    onValueChange = {
                        onEvent(UploadScreenEvent.OnStepChanged(state.steps[index].id, it))
                    }
                )
            }
            RecipeButton.DefaultTextButton(
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
                text = stringResource(Res.string.add_step),
                startIcon = painterResource(Res.drawable.close),
                onClick = {
                    onEvent(UploadScreenEvent.OnAddStepClicked(state.steps.size))
                }
            )
        }
    }
}

data class DraggableItem(val index: Int)