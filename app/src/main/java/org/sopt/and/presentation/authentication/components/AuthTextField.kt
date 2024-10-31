package org.sopt.and.presentation.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
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
import org.sopt.and.presentation.theme.White

@Composable
fun AuthTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    shape: Shape = RoundedCornerShape(8.dp),
    cursorBrush: Brush = SolidColor(WavveMain)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(shape)
            .background(ExtraDarkGray)
            .fillMaxWidth()
            .padding(16.dp),
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
            keyboardActions = keyboardActions, // TODO: 로그인, 회원가입 기능 추가
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = White
            ),
            cursorBrush = cursorBrush,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else visualTransformation,
        ) { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hint,
                    color = LightGray,
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
                    .noRippleClickable {
                        isPasswordVisible = !isPasswordVisible
                    },
                text = iconText,
                style = MaterialTheme.typography.bodyMedium,
                color = White
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