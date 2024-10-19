package id.deval.recipe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
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
import id.deval.recipe.theme.DefaultOutlineButtonStyle
import id.deval.recipe.theme.DefaultTextButtonStyle
import id.deval.recipe.theme.secondaryTextColor

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
        textStyle: TextStyle =  MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        ),
        color : ButtonColors = DefaultFilledButtonStyle()
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
                    color = if(enabled) color.contentColor else secondaryTextColor,
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
    fun DefaultOutlinedButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.shape,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        color : ButtonColors = DefaultOutlineButtonStyle(),
        border: BorderStroke? = BorderStroke(2.dp, color.contentColor),
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: String? = null,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
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
                    color = color.contentColor,
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
        color : ButtonColors = DefaultTextButtonStyle(),
        padding: PaddingValues = ButtonDefaults.ContentPadding,
        startIcon: Painter? = null,
        endIcon: Painter? = null,
        text: String? = null,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
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
                    color = color.contentColor,
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
}