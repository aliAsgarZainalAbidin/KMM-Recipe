package id.deval.recipe.ui.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeCommonUI.BoxUploadCoverPhoto
import id.deval.recipe.components.RecipeCommonUI.HeaderUploadStep
import id.deval.recipe.components.RecipeCommonUI.CheckWindowSizeClass
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.navigation.MainNavigation
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import id.deval.recipe.util.RecipeSliderValue
import id.deval.recipe.util.dashedBorder
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.description
import kmm_recipe.composeapp.generated.resources.enter_description
import kmm_recipe.composeapp.generated.resources.enter_food_name
import kmm_recipe.composeapp.generated.resources.food_name
import kmm_recipe.composeapp.generated.resources.next
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class UploadScreenFirstStep : Screen {

    @Composable
    override fun Content() {
        val uploadScreenViewModel by appRecipeModule.instance<UploadViewModel>()
        val uploadScreenState by uploadScreenViewModel.uploadScreenState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            uploadScreenViewModel.uploadScreenEffect.collectLatest { effect ->
                when (effect) {
                    is UploadScreenEffect.NavigateToSecondStep -> {
                        navigator?.push(MainNavigation.UploadSecond.screen)
                    }

                    is UploadScreenEffect.NavigateToDetail -> {}
                    is UploadScreenEffect.NavigateToHome -> {
                        navigator?.pop()
                    }

                    is UploadScreenEffect.ShowToast -> {}
                    is UploadScreenEffect.NavigateToFirstStep -> {}
                }
            }
        }

        UploadScreenContent(
            uploadScreenState,
            uploadScreenViewModel::onEvent
        )
    }

    @OptIn(
        ExperimentalLayoutApi::class,
        ExperimentalMaterial3Api::class
    )

    @Composable
    fun UploadScreenContent(
        state: UploadScreenState,
        onEvent: (UploadScreenEvent) -> Unit
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HeaderUploadStep(
                    onCancel = { onEvent(UploadScreenEvent.NavigateToHome) }
                )
                FlowRow(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalArrangement = Arrangement.Center,
                    maxItemsInEachRow = 2
                ) {
                    val customModifier = RecipeCommonUI.AdaptiveModifier(
                        compactModifier = Modifier.fillMaxWidth(),
                        mediumModifier = Modifier.weight(0.5f),
                        expandedModifier = Modifier.weight(0.5f),
                    )
                    val startSliderValue = 0f
                    val endSliderValue = 1f

                    val foodTitleModifier = RecipeCommonUI.AdaptiveModifier(
                        compactModifier = Modifier.padding(top = 24.dp),
                        mediumModifier = Modifier.padding(top = 0.dp),
                        expandedModifier = Modifier.padding(top = 0.dp)
                    )

                    val sliderState by remember {
                        mutableStateOf(
                            SliderState(
                                value = 0f,
                                steps = 1,
                                valueRange = startSliderValue..endSliderValue,
                            )
                        )
                    }
                    sliderState.onValueChangeFinished = {
                        val sliderValueInMinutes = when (sliderState.value) {
                            RecipeSliderValue.LESS_THAN_10.value -> RecipeSliderValue.LESS_THAN_10.inMinutesValue
                            RecipeSliderValue.EQUAL_30.value -> RecipeSliderValue.EQUAL_30.inMinutesValue
                            RecipeSliderValue.MORE_THAN_60.value -> RecipeSliderValue.MORE_THAN_60.inMinutesValue
                            else -> RecipeSliderValue.LESS_THAN_10.inMinutesValue
                        }
                        onEvent(UploadScreenEvent.OnDurationChanged(sliderValueInMinutes))
                    }

                    var heightSectionFoodDescription by remember { mutableStateOf(0.dp) }
                    val localDensity = LocalDensity.current

                    BoxUploadCoverPhoto(
                        modifier = customModifier
                            .padding(8.dp)
                            .heightIn(
                                min = heightSectionFoodDescription,
                                max = heightSectionFoodDescription
                            )
                            .dashedBorder(
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                    Column(
                        modifier = customModifier.onSizeChanged {
                            heightSectionFoodDescription = with(localDensity) { it.height.toDp() }
                        },
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Text(
                            text = stringResource(Res.string.food_name),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = foodTitleModifier
                        )
                        RecipeTextField.Outlined(
                            value = state.foodName,
                            placeholder = {
                                Text(
                                    text = stringResource(Res.string.enter_food_name),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onValueChange = { onEvent(UploadScreenEvent.OnFoodNameChanged(it)) },
                            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                            singleLine = true
                        )
                        Text(
                            text = stringResource(Res.string.description),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                        RecipeTextField.Outlined(
                            value = state.description,
                            shape = RoundedCornerShape(8.dp),
                            placeholder = {
                                Text(
                                    text = stringResource(Res.string.enter_description),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onValueChange = {
                                onEvent(UploadScreenEvent.OnDescriptionChanged(it))
                            },
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .heightIn(min = 120.dp, max = 120.dp)
                                .fillMaxWidth()
                        )
                    }
                    Column(
                        modifier = customModifier,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        RecipeCommonUI.RecipeDurationSlider(
                            sliderState,
                            startSliderValue,
                            endSliderValue
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        RecipeButton.DefaultFilledButton(
                            onClick = { onEvent(UploadScreenEvent.OnNextClicked) },
                            text = stringResource(Res.string.next),
                        )
                    }
                }
            }
        }
    }

}