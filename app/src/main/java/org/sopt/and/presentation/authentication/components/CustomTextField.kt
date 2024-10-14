package org.sopt.and.presentation.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.ExtraDarkGray
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain

@Composable
fun CustomTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = RoundedCornerShape(8.dp),
    cursorColor: Color = WavveMain
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.labelLarge,
                color = LightGray
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = ExtraDarkGray,
            focusedContainerColor = ExtraDarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = cursorColor,
        ),
        trailingIcon = {
            if (isPassword) {
                val iconText = if (isPasswordVisible) stringResource(R.string.hide) else stringResource(R.string.show)
                Text(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .noRippleClickable {
                            isPasswordVisible = !isPasswordVisible
                        },
                    text = iconText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else visualTransformation,
        keyboardOptions = if (isPassword) {
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        } else {
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        },
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