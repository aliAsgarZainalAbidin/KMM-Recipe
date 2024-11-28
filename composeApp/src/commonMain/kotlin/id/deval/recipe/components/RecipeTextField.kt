package id.deval.recipe.components

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import id.deval.recipe.domain.model.RecipeStep
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.theme.secondaryTextColor
import id.deval.recipe.util.isPhoneNumberValid
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.drag_icon
import org.jetbrains.compose.resources.painterResource

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
    fun Filled(
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
            focusedPrefixColor = mainTextColor,
            unfocusedLeadingIconColor = mainTextColor,
            focusedLeadingIconColor = secondaryTextColor,
            focusedTrailingIconColor = mainTextColor
        ),
    ) {
        val singleLine = false
        val maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
        val minLines = 1

        OutlinedTextField(
            value = value,
            onValueChange,
            modifier,
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
        @IntRange(from = 4, to = 6) totalOtpCode: Int = 4,
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

        val focusRequester = List(totalOtpCode) { FocusRequester() }

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(totalOtpCode) { index ->
                val char = when {
                    value.isEmpty() || index >= value.length -> ""
                    else -> if (value[index].isDigit()) value[index].toString() else ""
                }

                OutlinedTextField(
                    value = TextFieldValue(text = char, selection = TextRange(char.length)),
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
                    modifier = Modifier.weight(0.3f)
                        .focusRequester(focusRequester[index]),
                    onValueChange = { s ->
                        if (value.isEmpty() || index >= value.length) {
                            onValueChange(value + s.text)
                        } else {
                            if (index < value.length && index != totalOtpCode) {
                                val updatedOtp = if (s.text.isEmpty()) {
                                    value.replaceRange(
                                        index, index + 1,
                                        ""
                                    )
                                } else {
                                    value.replaceRange(
                                        index, index + 1,
                                        s.text.last().toString()
                                    )
                                }
                                onValueChange(updatedOtp)
                            }
                        }

                        if(s.text.isEmpty()){
                            if (index - 1 != -1){
                                focusRequester[index - 1].requestFocus()
                            }
                        } else {
                            if (value.length + 1 != totalOtpCode && "\\d".toRegex().matches(s.text)) {
                                focusRequester[value.length + 1].requestFocus()
                            }
                        }
                    },
                    colors = color
                )
                if (index < totalOtpCode - 1) {
                    Spacer(
                        modifier = Modifier.weight(0.1f)
                    )
                }
            }
        }
    }

    @Composable
    fun DragableTextField(
        modifier: Modifier,
        onDragStarted: () -> Unit = {},
        value: String,
        onValueChange: (String) -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = modifier,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.drag_icon),
                    contentDescription = "dragable icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Outlined(
                value = value,
                onValueChange = onValueChange,
                singleLine = true
            )
        }
    }

    @Composable
    fun DragableStepInputField(
        modifier: Modifier,
        numberSteps: String,
        value: RecipeStep,
        onDragStarted: () -> Unit = {},
        onValueChange: (String) -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(100))
                        .background(mainTextColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = numberSteps,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = modifier,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.drag_icon),
                        contentDescription = "dragable icon",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Column(
                modifier = Modifier
            ) {
                Outlined(
                    value = value.description,
                    onValueChange = onValueChange,
                    singleLine = false,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .heightIn(min = 120.dp, max = 120.dp),
                    maxLines = 4
                )
                RecipeButton.UploadImageButton(
                    onClick = {},
                    onDeleteClick = {},
                    pathImage = value.image
                )
            }
        }
    }


}