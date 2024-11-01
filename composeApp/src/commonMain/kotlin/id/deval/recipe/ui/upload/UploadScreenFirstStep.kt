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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
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
import kmm_recipe.composeapp.generated.resources.first_step
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

    @OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
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
                    BoxUploadCoverPhoto(
                        modifier = Modifier
                            .padding(2.dp)
                            .widthIn(max = 600.dp)
                            .dashedBorder(
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
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