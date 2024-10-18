package id.deval.recipe.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor

object RecipeTextField {

    @Composable
    fun Outlined(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
            color = mainTextColor
        ),
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        prefix: @Composable (() -> Unit)? = null,
        suffix: @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = false,
        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
        minLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = RoundedCornerShape(100),
        colors: TextFieldColors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = mainTextColor,
            unfocusedTextColor = mainTextColor,
            focusedPlaceholderColor = secondaryTextColor,
            unfocusedPlaceholderColor = secondaryTextColor,
            unfocusedPrefixColor = secondaryTextColor,
            focusedPrefixColor = mainTextColor
        ),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange,
            modifier.fillMaxWidth(),
            enabled,
            readOnly,
            textStyle,
            label,
            placeholder,
            leadingIcon,
            trailingIcon,
            prefix,
            suffix,
            supportingText,
            isError,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            singleLine,
            maxLines,
            minLines,
            interactionSource,
            shape,
            colors
        )
    }


}