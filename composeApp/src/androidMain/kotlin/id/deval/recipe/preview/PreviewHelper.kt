package id.deval.recipe.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.theme.RecipeAppTheme
import id.deval.recipe.theme.white
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.back_to_home
import kmm_recipe.composeapp.generated.resources.hbday
import kmm_recipe.composeapp.generated.resources.recipe_uploaded
import kmm_recipe.composeapp.generated.resources.upload_success
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun RecipeDialog() {
    Column(
        modifier = Modifier
            .padding(
                top = 48.dp, bottom = 48.dp, start = 42.dp, end = 42.dp
            )
            .background(white),
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
//                onEvent(UploadScreenEvent.ShowDialog(false))
            },
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(Res.string.back_to_home)
        )
    }
}

@Preview
@Composable
fun AppPreview(){
    RecipeAppTheme {
        RecipeDialog()
    }
}