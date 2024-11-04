package id.deval.recipe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.deval.recipe.theme.DefaultFilledButtonStyle
import id.deval.recipe.theme.DefaultIconButtonColors
import id.deval.recipe.theme.DefaultOutlineButtonStyle
import id.deval.recipe.theme.DefaultTextButtonStyle
import id.deval.recipe.theme.red
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.theme.white
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.baseline_favorite_24
import kmm_recipe.composeapp.generated.resources.baseline_favorite_border_24
import kmm_recipe.composeapp.generated.resources.heart
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object RecipeButton {

    @Composable
    fun DefaultFilledButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.shape,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        border: BorderStroke? = null,
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: String? = null,
        textStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        ),
        color: ButtonColors = DefaultFilledButtonStyle()
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = color,
            enabled = enabled,
            shape = shape,
            elevation = elevation,
            border = border,
            contentPadding = padding
        ) {
            if (startIcon != null) {
                Icon(
                    painter = startIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            if (text != null) {
                Text(
                    text = text,
                    color = if (enabled) color.contentColor else secondaryTextColor,
                    fontWeight = FontWeight.Bold,
                    style = textStyle
                )
            }
            if (endIcon != null) {
                Icon(
                    painter = endIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

    @Composable
    fun DefaultFilledChipButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier
            .fillMaxWidth(),
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.shape,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        border: BorderStroke? = null,
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: StringResource? = null,
        textStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        ),
    ) {
        val color = DefaultFilledButtonStyle().copy(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            contentColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        )

        Button(
            onClick = onClick,
            modifier = modifier,
            colors = color,
            shape = shape,
            elevation = elevation,
            border = border,
            contentPadding = padding
        ) {
            if (startIcon != null) {
                Icon(
                    painter = startIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            if (text != null) {
                Text(
                    text = stringResource(text),
                    color = if (enabled) color.contentColor else secondaryTextColor,
                    fontWeight = FontWeight.Normal,
                    style = textStyle
                )
            }
            if (endIcon != null) {
                Icon(
                    painter = endIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

    @Composable
    fun DefaultOutlinedButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.shape,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        color: ButtonColors = DefaultOutlineButtonStyle(),
        border: BorderStroke? = BorderStroke(2.dp, color.contentColor),
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: String? = null,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = color,
            enabled = enabled,
            shape = shape,
            elevation = elevation,
            border = border,
            contentPadding = padding
        ) {
            if (startIcon != null) {
                Icon(
                    painter = startIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            if (text != null) {
                Text(
                    text = text,
                    color = if (enabled) color.contentColor else secondaryTextColor,
                    fontWeight = FontWeight.Bold
                )
            }
            if (endIcon != null) {
                Icon(
                    painter = endIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }


    @Composable
    fun DefaultTextButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.textShape,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        border: BorderStroke? = null,
        color: ButtonColors = DefaultTextButtonStyle(),
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: String? = null,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = color,
            enabled = enabled,
            shape = shape,
            elevation = elevation,
            border = border,
            contentPadding = padding
        ) {
            if (startIcon != null) {
                Icon(
                    painter = startIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            if (text != null) {
                Text(
                    text = text,
                    color = if (enabled) color.contentColor else secondaryTextColor,
                    fontWeight = FontWeight.Bold
                )
            }
            if (endIcon != null) {
                Icon(
                    painter = endIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

    @Composable
    fun DefaultCircleFilledButton(
        icon: Painter,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        color: IconButtonColors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier.size(56.dp),
            colors = color,
            enabled = enabled
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

    @Composable
    fun LikeIconButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        checked : Boolean = false,
        enabled: Boolean = true,
        color: IconButtonColors = DefaultIconButtonColors()
    ) {
        val icon = if(checked) painterResource(Res.drawable.baseline_favorite_24) else painterResource(Res.drawable.baseline_favorite_border_24)
        val iconColor = if(checked) red else white
        IconButton(
            onClick = onClick,
            modifier = modifier.size(48.dp),
            colors = color,
            enabled = enabled
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = iconColor
            )
        }
    }
}