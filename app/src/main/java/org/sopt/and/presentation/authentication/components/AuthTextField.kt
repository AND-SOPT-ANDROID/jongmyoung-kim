package org.sopt.and.presentation.authentication.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun AuthTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    cursorBrush: Brush = SolidColor(AndAndroidTheme.colors.wavveMain)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = if (isPassword) {
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            } else {
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            },
            keyboardActions = keyboardActions,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = AndAndroidTheme.colors.white
            ),
            cursorBrush = cursorBrush,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else visualTransformation,
        ) { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hint,
                    color = AndAndroidTheme.colors.gray100,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    )
                )
            }
            innerTextField()
        }
        if (isPassword) {
            val iconText =
                if (isPasswordVisible) stringResource(R.string.hide)
                else stringResource(R.string.show)
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .noRippleClickable(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ),
                text = iconText,
                style = MaterialTheme.typography.bodyMedium,
                color = AndAndroidTheme.colors.white
            )
        }
    }
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    ANDANDROIDTheme {
        AuthTextField(
            value = "",
            hint = "hint",
            onValueChange = {}
        )
    }
}
