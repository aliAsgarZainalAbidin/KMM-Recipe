package id.deval.recipe.ui.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeCommonUI.BoxUploadCoverPhoto
import id.deval.recipe.components.RecipeCommonUI.HeaderUploadStep
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.navigation.MainNavigation
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import id.deval.recipe.util.RecipeSliderValue
import id.deval.recipe.util.dashedBorder
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.cooking_duration
import kmm_recipe.composeapp.generated.resources.cooking_duration_in_minutes
import kmm_recipe.composeapp.generated.resources.description
import kmm_recipe.composeapp.generated.resources.enter_description
import kmm_recipe.composeapp.generated.resources.enter_food_name
import kmm_recipe.composeapp.generated.resources.equal_30
import kmm_recipe.composeapp.generated.resources.food_name
import kmm_recipe.composeapp.generated.resources.less_than_10
import kmm_recipe.composeapp.generated.resources.more_than_60
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
        ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class,
        ExperimentalMaterial3Api::class
    )
    @Composable
    fun UploadScreenContent(
        state: UploadScreenState,
        onEvent: (UploadScreenEvent) -> Unit
    ) {
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

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
                    val isCompact =
                        windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
                    val customModifier = if (isCompact) {
                        Modifier.fillMaxWidth()
                    } else {
                        Modifier.weight(0.5f)
                    }
                    val startSliderValue = 0f
                    val endSliderValue = 1f

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
                            modifier = Modifier.padding(top = if (isCompact) 24.dp else 0.dp)
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