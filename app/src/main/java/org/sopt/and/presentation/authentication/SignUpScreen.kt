package org.sopt.and.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.authentication.components.CustomTextField
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.util.Constants

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: (String, String) -> Unit = { _, _ -> }
) {
    val scrollState = rememberScrollState()
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 12.dp),
                text = buildAnnotatedString {
                    append(stringResource(R.string.sign_up_greeting))
                    addStyle(
                        style = SpanStyle(color = Color.White),
                        start = 0,
                        end = 9
                    )
                    addStyle(
                        style = SpanStyle(color = LightGray),
                        start = 9,
                        end = 12
                    )
                    addStyle(
                        style = SpanStyle(color = Color.White),
                        start = 13,
                        end = 24
                    )
                    addStyle(
                        style = SpanStyle(color = LightGray),
                        start = 25,
                        end = 29
                    )
                },
                style = MaterialTheme.typography.titleLarge
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 4.dp)
                        ,
                value = emailInput,
                hint = stringResource(R.string.sign_up_email_hint),
                onValueChange = {
                    if (it.length <= Constants.MAX_EMAIL) emailInput = it
                }
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = stringResource(R.string.sign_up_email_noti),
                textColor = LightGray
            )
            CustomTextField(
                modifier = Modifier,
                value = passwordInput,
                hint = stringResource(R.string.sign_up_password_hint),
                onValueChange = {
                    if (it.length <= Constants.MAX_PASSWORD) passwordInput = it
                },
                isPassword = true,
                visualTransformation = PasswordVisualTransformation()
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = stringResource(R.string.sign_up_password_noti),
                textColor = LightGray
            )
            SnsAccountTab(
                modifier = Modifier.padding(bottom = 96.dp),
                title = stringResource(R.string.sns_sign_up)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(48.dp)
                .fillMaxWidth()
                .background(LightGray)
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSignUpClick(emailInput, passwordInput)
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.wavve_sign_up),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            onSignUpClick = { _, _ -> }
        )
    }
}