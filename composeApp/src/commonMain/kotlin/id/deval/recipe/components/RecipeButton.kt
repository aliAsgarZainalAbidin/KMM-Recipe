package id.deval.recipe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.deval.recipe.theme.DefaultFilledButtonStyle
import id.deval.recipe.theme.DefaultIconButtonColors
import id.deval.recipe.theme.DefaultOutlineButtonStyle
import id.deval.recipe.theme.DefaultTextButtonStyle
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.red
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.theme.white
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.back
import kmm_recipe.composeapp.generated.resources.baseline_favorite_24
import kmm_recipe.composeapp.generated.resources.baseline_favorite_border_24
import kmm_recipe.composeapp.generated.resources.camera
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object RecipeButton {

    @Composable
    fun DefaultFilledButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
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
    fun FollowButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
        isFollow: Boolean = true,
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
        color: ButtonColors = if (!isFollow) DefaultFilledButtonStyle() else DefaultOutlineButtonStyle().copy(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.tertiary,
        )
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .height(56.dp),
            colors = color,
            enabled = true,
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
                    color = if (!isFollow) color.contentColor else secondaryTextColor,
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
        color: ButtonColors = DefaultFilledButtonStyle().copy(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            contentColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        ),
        text: StringResource? = null,
        textStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        ),
    ) {
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
        checked: Boolean = false,
        enabled: Boolean = true,
        color: IconButtonColors = DefaultIconButtonColors()
    ) {
        val icon =
            if (checked) painterResource(Res.drawable.baseline_favorite_24) else painterResource(Res.drawable.baseline_favorite_border_24)
        val iconColor = if (checked) red else white
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

    @Composable
    fun CircleBackButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        color: IconButtonColors = DefaultIconButtonColors(),
        colorIcon: Color = white
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier.size(48.dp),
            colors = color,
            enabled = true
        ) {
            Icon(
                painter = painterResource(Res.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colorIcon
            )
        }
    }

    @Composable
    fun BackButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        color: IconButtonColors = DefaultIconButtonColors(),
        colorIcon: Color = mainTextColor
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier.size(48.dp),
            colors = color.copy(
                containerColor = Color.Transparent
            ),
            enabled = true
        ) {
            Icon(
                painter = painterResource(Res.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colorIcon
            )
        }
    }

    @Composable
    fun UploadImageButton(
        onClick: () -> Unit,
        onDeleteClick: () -> Unit,
        pathImage: String?,
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (pathImage != null) {
                Image(
                    painter = painterResource(Res.drawable.Onboarding),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.wrapContentSize()
                )
            } else {
                DefaultFilledChipButton(
                    onClick = onClick,
                    shape = RoundedCornerShape(8.dp),
                    color = DefaultFilledButtonStyle().copy(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = mainTextColor,
                    ),
                    startIcon = painterResource(Res.drawable.camera)
                )
            }
        }
    }
}