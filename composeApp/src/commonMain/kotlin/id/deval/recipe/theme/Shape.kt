package id.deval.recipe.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun DefaultFilledButtonStyle() = buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultCircleFilledButtonStyle() = IconButtonDefaults.iconButtonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultOutlineButtonStyle() = buttonColors(
    containerColor = MaterialTheme.colorScheme.onPrimary,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultTextButtonStyle() = buttonColors(
    containerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultRedFilledButtonStyle() = buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultRedOutlineButtonStyle() = buttonColors(
    containerColor = MaterialTheme.colorScheme.onPrimary,
    contentColor = MaterialTheme.colorScheme.secondary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultRedTextButtonStyle() = buttonColors(
    containerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.secondary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledContentColor = MaterialTheme.colorScheme.tertiary
)

@Composable
fun DefaultIconButtonColors() = IconButtonDefaults.iconButtonColors(
    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
    disabledContentColor = MaterialTheme.colorScheme.onPrimary
)


@Composable
@Preview
fun PreviewStyleComponent(){
    RecipeAppTheme {
        Button(
            onClick = {},
            colors = DefaultFilledButtonStyle()
        ) {
            Text("Click me !")
        }
    }
}
