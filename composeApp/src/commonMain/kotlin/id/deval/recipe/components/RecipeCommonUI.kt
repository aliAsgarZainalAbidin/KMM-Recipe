package id.deval.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_cover_photo
import kmm_recipe.composeapp.generated.resources.baseline_image_24
import kmm_recipe.composeapp.generated.resources.cancel
import kmm_recipe.composeapp.generated.resources.first_step
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

}