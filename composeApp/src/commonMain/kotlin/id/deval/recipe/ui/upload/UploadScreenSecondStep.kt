package id.deval.recipe.ui.upload

import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeCommonUI.HeaderUploadStep
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_ingredient
import kmm_recipe.composeapp.generated.resources.add_step
import kmm_recipe.composeapp.generated.resources.close
import kmm_recipe.composeapp.generated.resources.ingredients
import kmm_recipe.composeapp.generated.resources.steps
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class UploadScreenSecondStep : Screen {

    @Composable
    override fun Content() {
        val uploadScreenViewModel by appRecipeModule.instance<UploadViewModel>()
        val uploadScreenState by uploadScreenViewModel.uploadScreenState.collectAsStateWithLifecycle()

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
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 12.dp, bottom = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HeaderUploadStep(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                )
                Column(
                    modifier = Modifier.verticalScroll(
                        state = rememberScrollState(),
                    )
                ) {
                    SectionIngredients(
                        state = state,
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                        onEvent = onEvent
                    )
                    RecipeCommonUI.RecipeSpacer()
                    SectionSteps(
                        state = state,
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                        onEvent = onEvent
                    )
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
        Column(modifier){
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
        ){
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