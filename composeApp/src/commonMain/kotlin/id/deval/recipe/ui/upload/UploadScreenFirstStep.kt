package id.deval.recipe.ui.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import id.deval.recipe.util.dashedBorder
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_cover_photo
import kmm_recipe.composeapp.generated.resources.baseline_image_24
import kmm_recipe.composeapp.generated.resources.camera
import kmm_recipe.composeapp.generated.resources.cancel
import kmm_recipe.composeapp.generated.resources.cooking_duration
import kmm_recipe.composeapp.generated.resources.description
import kmm_recipe.composeapp.generated.resources.equal_30
import kmm_recipe.composeapp.generated.resources.first_step
import kmm_recipe.composeapp.generated.resources.food_name
import kmm_recipe.composeapp.generated.resources.less_than_10
import kmm_recipe.composeapp.generated.resources.more_than_60
import kmm_recipe.composeapp.generated.resources.next
import kmm_recipe.composeapp.generated.resources.second_step
import kmm_recipe.composeapp.generated.resources.send_again
import kmm_recipe.composeapp.generated.resources.separator_step
import kmm_recipe.composeapp.generated.resources.up_to_mb
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class UploadScreenFirstStep : Screen {

    @Composable
    override fun Content() {
        val uploadScreenViewModel by appRecipeModule.instance<UploadViewModel>()
        val uploadScreenState by uploadScreenViewModel.uploadScreenState.collectAsStateWithLifecycle()

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(Res.string.cancel),
                        modifier = Modifier,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    UploadStepText(Res.string.first_step)
                }
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
                                valueRange = startSliderValue..endSliderValue
                            )
                        )
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
                    Logger.d(
                        tag = "SLIDER-STATE",
                        messageString = "${sliderState.value}"
                    )
                    Column(
                        modifier = customModifier.onSizeChanged {
                            heightSectionFoodDescription = with(localDensity) {
                                it.height.toDp()
                            }
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
                            onValueChange = {
                                onEvent(UploadScreenEvent.OnFoodNameChanged(it))
                            },
                            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
                        )
                        Text(
                            text = stringResource(Res.string.description),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                        RecipeTextField.Outlined(
                            value = state.description,
                            shape = RoundedCornerShape(8.dp),
                            onValueChange = {
                                onEvent(UploadScreenEvent.OnDescriptionChanged(it))
                            },
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .heightIn(min = 80.dp)
                                .fillMaxWidth()
                        )
                    }
                    Column(
                        modifier = customModifier,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Text(
                            text = stringResource(Res.string.cooking_duration),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(Res.string.less_than_10),
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = if (sliderState.value >= startSliderValue)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onSurface
                                    ),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = stringResource(Res.string.equal_30),
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = if (sliderState.value >= endSliderValue.div(2))
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onSurface
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = stringResource(Res.string.more_than_60),
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = if (sliderState.value >= endSliderValue)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onSurface
                                    ),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Slider(
                                state = sliderState,
                                colors = SliderDefaults.colors().copy(
                                    inactiveTickColor = Color.Transparent,
                                    activeTickColor = Color.Transparent,
                                    disabledActiveTickColor = Color.Transparent,
                                    disabledInactiveTickColor = Color.Transparent
                                ),
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 8.dp),
                                thumb = {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .background(
                                                MaterialTheme.colorScheme.primary,
                                                CircleShape
                                            )
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(Color.Transparent)
                                            .padding(2.dp),
                                        content = {}
                                    )
                                },
                                track = {
                                    val fraction by remember {
                                        derivedStateOf {
                                            (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
                                        }
                                    }
                                    Box(Modifier.fillMaxWidth()) {
                                        Box(
                                            Modifier
                                                .fillMaxWidth(fraction)
                                                .align(Alignment.CenterStart)
                                                .height(8.dp)
                                                .padding(end = 16.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    CircleShape
                                                )
                                        )
                                        Box(
                                            Modifier
                                                .fillMaxWidth(1f - fraction)
                                                .align(Alignment.CenterEnd)
                                                .height(8.dp)
                                                .padding(start = 16.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.outline,
                                                    CircleShape
                                                )
                                        )
                                    }
                                }
                            )
                        }
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        RecipeButton.DefaultFilledButton(
                            onClick = {},
                            modifier = Modifier,
                            text = stringResource(Res.string.next),
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun UploadStepText(
        currentStep: StringResource
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = mainTextColor)) {
                    append(stringResource(currentStep))
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = secondaryTextColor
                    )
                ) {
                    append(stringResource(Res.string.separator_step))
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = secondaryTextColor
                    )
                ) {
                    append(stringResource(Res.string.second_step))
                }
            },
            style = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun BoxUploadCoverPhoto(
        modifier: Modifier
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(Res.drawable.baseline_image_24),
                    contentDescription = "image",
                    modifier = Modifier.size(56.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(Res.string.add_cover_photo),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = mainTextColor
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = stringResource(Res.string.up_to_mb),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = secondaryTextColor
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}