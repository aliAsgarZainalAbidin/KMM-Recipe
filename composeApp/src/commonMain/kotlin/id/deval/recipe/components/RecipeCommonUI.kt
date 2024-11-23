package id.deval.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.ui.home.FilterCategory
import id.deval.recipe.ui.home.event.HomeScreenEvent
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_cover_photo
import kmm_recipe.composeapp.generated.resources.baseline_image_24
import kmm_recipe.composeapp.generated.resources.cancel
import kmm_recipe.composeapp.generated.resources.cooking_duration
import kmm_recipe.composeapp.generated.resources.cooking_duration_in_minutes
import kmm_recipe.composeapp.generated.resources.equal_30
import kmm_recipe.composeapp.generated.resources.first_step
import kmm_recipe.composeapp.generated.resources.less_than_10
import kmm_recipe.composeapp.generated.resources.more_than_60
import kmm_recipe.composeapp.generated.resources.second_step
import kmm_recipe.composeapp.generated.resources.separator_step
import kmm_recipe.composeapp.generated.resources.up_to_mb
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object RecipeCommonUI {

    @Composable
    fun HeaderUploadStep(
        onCancel : () -> Unit,
        modifier : Modifier = Modifier,
        stringResource: StringResource = Res.string.first_step
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(Res.string.cancel),
                modifier = Modifier.clickable {
                    onCancel()
                },
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
            )
            UploadStepText(stringResource)
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

    @Composable
    fun RecipeSpacer(){
        Spacer(
            modifier = Modifier.height(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.tertiary)
        )
    }

    @Composable
    fun RecipeDialog(
        modifier : Modifier = Modifier,
        onDismissRequest : () -> Unit,
        content : @Composable () -> Unit = {}
    ){
        Dialog(
            onDismissRequest = onDismissRequest
        ){
            Card(
                modifier = modifier
            ){
                content()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RecipeDurationSlider(
        sliderState : SliderState,
        startSliderValue : Float = 0f,
        endSliderValue : Float = 1f
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = mainTextColor
                        )
                    ) {
                        append(stringResource(Res.string.cooking_duration))
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            color = secondaryTextColor
                        )
                    ) {
                        append(stringResource(Res.string.cooking_duration_in_minutes))
                    }
                },
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
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
    }


    @Composable
    fun FilterCategory(
        modifier: Modifier,
        categories: List<FilterCategory>,
        stateCategory: FilterCategory,
        onEvent: ((HomeScreenEvent) -> Unit)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            repeat(categories.size) { index ->
                RecipeButton.DefaultFilledChipButton(
                    onClick = {
                        onEvent(HomeScreenEvent.OnCategorySelected(categories[index]))
                    },
                    modifier = Modifier.wrapContentSize()
                        .padding(end = 8.dp)
                        .defaultMinSize(minWidth = 56.dp),
                    text = categories[index].name,
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Normal
                    ),
                    enabled = stateCategory == categories[index]
                )
            }
        }
    }

    @Composable
    fun FilterCategoryCallback(
        modifier: Modifier,
        categories: List<FilterCategory>,
        stateCategory: FilterCategory,
        callback: ((FilterCategory) -> Unit)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            repeat(categories.size) { index ->
                RecipeButton.DefaultFilledChipButton(
                    onClick = {
                        callback(categories[index])
                    },
                    modifier = Modifier.wrapContentSize()
                        .padding(end = 8.dp)
                        .defaultMinSize(minWidth = 56.dp),
                    text = categories[index].name,
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Normal
                    ),
                    enabled = stateCategory == categories[index]
                )
            }
        }
    }
}