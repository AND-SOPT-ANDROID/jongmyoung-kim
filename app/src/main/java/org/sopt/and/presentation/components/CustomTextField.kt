package org.sopt.and.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Container
import org.sopt.and.ui.theme.LightGray


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Container,
            focusedContainerColor = Container,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White
        ),
        trailingIcon = {
            if(isPassword) {
                val iconText = if (isPasswordVisible) "hide" else "show"
                Text(
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ),
                    text = iconText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        },
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        singleLine = true,
        shape = shape
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    ANDANDROIDTheme {
        CustomTextField(
            value = "",
            hint = "hint",
            onValueChange = {}
        )
    }
}