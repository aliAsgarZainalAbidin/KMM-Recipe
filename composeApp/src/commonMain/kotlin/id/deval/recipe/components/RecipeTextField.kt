package id.deval.recipe.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
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

    @Composable
    fun OtpTextField(
        value: String,
        totalOtpCode: Int = 4,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
    ) {
        val keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        val color = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = mainTextColor,
            unfocusedTextColor = mainTextColor,
            focusedPlaceholderColor = secondaryTextColor,
            unfocusedPlaceholderColor = secondaryTextColor,
            unfocusedPrefixColor = secondaryTextColor,
            focusedPrefixColor = mainTextColor
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(totalOtpCode) { index ->
                val char = when {
                    value.isEmpty() || index >= value.length -> ""
                    else -> value[index].toString()
                }

                OutlinedTextField(
                    value = char,
                    maxLines = 1,
                    minLines = 1,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = mainTextColor,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = keyboardOptions,
                    modifier = Modifier.weight(1f),
                    onValueChange = { s ->
                        if (value.isEmpty() || index >= value.length) {
                            onValueChange(value + s)
                        } else {
                            onValueChange(value.replaceRange(index, index + 1, s))
                        }
                    },
                    colors = color
                )
                if(index < totalOtpCode - 1) {
                    Spacer(
                        modifier = Modifier.weight(0.2f)
                    )
                }
            }
        }
    }


}